package com.thejaum.challenge.sandwich.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thejaum.challenge.sandwich.dto.AddItemOrderDTO;
import com.thejaum.challenge.sandwich.models.Additional;
import com.thejaum.challenge.sandwich.models.Addon;
import com.thejaum.challenge.sandwich.models.Offer;
import com.thejaum.challenge.sandwich.models.Order;
import com.thejaum.challenge.sandwich.models.OrderItem;
import com.thejaum.challenge.sandwich.models.Product;
import com.thejaum.challenge.sandwich.repository.OrderItemRepository;
import com.thejaum.challenge.sandwich.repository.OrderRepository;
import com.thejaum.challenge.sandwich.repository.custom.OrderRepositoryCustom;
import com.thejaum.challenge.sandwich.util.OrderStatusPattern;

@Service
public class OrderService {
	
	@Autowired
	private final OrderRepository repository;
	@Autowired
	private final OrderRepositoryCustom customRepository;
	@Autowired
	private final OrderItemRepository itemRepository;
	@Autowired
	private final ProductService productService;
	@Autowired
	private final AddonService addonService;
	@Autowired
	private final AdditionalService additionalService;
	@Autowired
	private final OfferService offerService;
	
	public OrderService(
			OrderRepository repository,
			OrderRepositoryCustom customRepository,
			ProductService productService,
			AddonService addonService,
			OrderItemRepository itemRepository,
			AdditionalService additionalService,
			OfferService offerService) {
		this.repository = repository;
		this.customRepository = customRepository;
		this.productService = productService;
		this.addonService = addonService;
		this.itemRepository = itemRepository;
		this.additionalService = additionalService;
		this.offerService = offerService;
	}
	
	public Order createNewOrder() {
		Order order = new Order();
		order.setCreated_at(LocalDateTime.now());
		order.setStatus(OrderStatusPattern.EM_ANDAMENTO.toString());
		return repository.save(order);
	}
	
	public List<Order> getAllByQueryStringParameters(String status){
		return customRepository.getOrdersByQueryStringParameters(status);
	}
	
	public Optional<Order> getOrderById(UUID id) {
		return repository.findById(id);
	}
	
	public OrderItem addItem(AddItemOrderDTO input,UUID order_id) {
		Optional<Product> product = productService.getProductById(input.getProductId());
		Optional<Order> order = getOrderById(order_id);
		OrderItem item = new OrderItem(); 
		if(product.isPresent() && order.isPresent()) {
			List<UUID> allAddonsItem = new ArrayList<>();
			allAddonsItem.addAll(product.get().getComposition()
					.stream()
					.map(Addon::getId)
		            .collect(Collectors.toList()));
			item.setValue(new BigDecimal("0.0"));
			item.setValue(item.getValue().add(product.get().getBasePrice()));
			item.setValue(item.getValue().add(addonService.calcPriceByAddonList(product.get().getComposition())));
			item.setProduct(product.get());
			item.setOrder(order.get());
			item = itemRepository.save(item);
			if(!input.getAdditionals().isEmpty()) {
				List<Additional> additionals = additionalService.addAditionalsByAddonsIdList(input.getAdditionals(), item);
				allAddonsItem.addAll(input.getAdditionals());
				for(Additional additional : additionals){
					item.setValue(item.getValue().add(
							additional.getAddon().getPrice().multiply(new BigDecimal(additional.getAmount()))));
				}
				itemRepository.save(item);
			}
			List<Offer> promotions = offerService.checkIfAddonsMetAnyOfferRules(allAddonsItem);
			if(promotions.size() > 0) {
				for(Offer offer : promotions) {
					double percentage = (double)offer.getDiscount() / (double)100;
					if(offer.isWholeOrder()) {
						item.setValue(item.getValue().subtract(
							item.getValue()
							.multiply(BigDecimal.valueOf(percentage))));
					}else {
						BigDecimal addonPrice = offer.getRules()
							.stream().findFirst().get().getAddon().getPrice();
						addonPrice = addonPrice.multiply(BigDecimal.valueOf(offer.getRules()
							.stream().findFirst().get().getAmount()));
						addonPrice = addonPrice.multiply(BigDecimal.valueOf(percentage));
						addonPrice = addonPrice.setScale(0, RoundingMode.UP);
						item.setValue(item.getValue().subtract(addonPrice));
					}
				}
				item.setPromotions(promotions);
				itemRepository.save(item);
			}
		}else {
			//Throw error : Product or Order not Found
		}
		return item;
	}

}
