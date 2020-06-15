package com.thejaum.challenge.sandwich.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.stereotype.Service;

import com.thejaum.challenge.sandwich.models.Offer;
import com.thejaum.challenge.sandwich.models.OfferRule;
import com.thejaum.challenge.sandwich.repository.OfferRepository;

@Service
public class OfferService {
	private final OfferRepository repository;

	public OfferService(OfferRepository respository) {
		super();
		this.repository = respository;
	}
	
	public List<Offer> getActiveOfferList() {
		return repository.getOfferByActiveTrue();
	}
	
	public List<Offer> getOfferList() {
		return IterableUtils.toList(repository.findAll());
	}
	
	public Optional<Offer> getOfferById(UUID id) {
		return repository.findById(id);
	}
	
	public List<Offer> checkIfAddonsMetAnyOfferRules(List<UUID> addonList) {
		List<Offer> activeOffers = getActiveOfferList();
		List<Offer> offersThatHaveMet = new ArrayList<Offer>();
		
		Map<UUID,Long> mapAddonAmount = addonList.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		for(Offer offer : activeOffers) {
			int numberOfRules = offer.getRules().size();
			int numberOfRulesMet = 0;
			do {
				numberOfRulesMet = 0;
				for(OfferRule rule : offer.getRules()) {
					if(rule.isPresence() 
						&& mapAddonAmount.containsKey(rule.getAddon().getId())) {
						Long amountFound = mapAddonAmount.get(rule.getAddon().getId());
						if(amountFound >= rule.getAmount()) { 
							mapAddonAmount.put(
									rule.getAddon().getId(), Long.valueOf(amountFound - rule.getAmount()));
							numberOfRulesMet += 1;
						}
					}else if(!rule.isPresence()
							&& !mapAddonAmount.containsKey(rule.getAddon().getId())){
						numberOfRulesMet += 1;
					}
				};
				if(numberOfRules == numberOfRulesMet) {
					offersThatHaveMet.add(offer);
				}
			}while(offer.isAccumulative() && (numberOfRules == numberOfRulesMet));	
		};
		return offersThatHaveMet;
	}
	
	
	/*Long amountFound = addonList.stream()
	.map(x -> x.compareTo(rule.getAddon().getId()))
	.count();
if((amountFound >= rule.getAmount() && rule.isPresence())
   || (amountFound <= rule.getAmount() && !rule.isPresence())) 
	continue;
isPromotional = false;*/
	
}
