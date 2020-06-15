package com.thejaum.challenge.sandwich.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thejaum.challenge.sandwich.models.Additional;
import com.thejaum.challenge.sandwich.models.Addon;
import com.thejaum.challenge.sandwich.models.OrderItem;
import com.thejaum.challenge.sandwich.repository.AdditionalRepository;

@Service
public class AdditionalService {
	
	@Autowired
	private final AdditionalRepository repository;
	@Autowired
	private final AddonService addonService;

	public AdditionalService(AdditionalRepository repository,
			AddonService addonService) {
		super();
		this.repository = repository;
		this.addonService = addonService;
	}
	
	public Additional createAdditional(Addon addon,OrderItem orderItem,int amount){
		Additional additional = new Additional();
		additional.setAddon(addon);
		additional.setOrderItem(orderItem);
		additional.setAmount(amount);
		repository.save(additional);
		return additional;
	}
	
	public List<Additional> addAditionalsByAddonsIdList(List<UUID> addonList,OrderItem orderItem){
		List<Additional> additionals = new ArrayList<Additional>();
		Map<UUID,Long> map = addonList.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		for (Map.Entry<UUID,Long> entry : map.entrySet()) {
			Optional<Addon> addon = addonService.getAddonsById(entry.getKey());
			if(!addon.isPresent()) {}
				//Throw error : Additional addon not found
			additionals.add(createAdditional(addon.get(), orderItem, Math.toIntExact(entry.getValue())));
		}
		return additionals;
	}
}
