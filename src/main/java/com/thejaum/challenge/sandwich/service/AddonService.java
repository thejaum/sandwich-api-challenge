package com.thejaum.challenge.sandwich.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.thejaum.challenge.sandwich.models.Addon;
import com.thejaum.challenge.sandwich.repository.AddonRepository;

@Service
public class AddonService {

	private final AddonRepository repository;

	public AddonService(AddonRepository repository) {
		super();
		this.repository = repository;
	}
	
	public Optional<Addon> getAddonsById(UUID id){
		return repository.findById(id);
	}
	public List<Addon> getAddonsByIdList(List<UUID> addonIdList){
		List<Addon> addonList = new ArrayList<Addon>();
		addonIdList.forEach(addonId -> {
			Optional<Addon> addon = addonList.stream()
					.filter(x -> x.getId() == addonId)
					.findFirst();
			if(addon.isPresent()) {
				addonList.add(addon.get());
			}else {
				addon = getAddonsById(addonId);
				if(addon.isPresent())
					addonList.add(addon.get());
			}
		});
		return addonList;
	}
	
	public BigDecimal calcPriceByAddonList(List<Addon> addonList) {
		 BigDecimal sum = addonList.stream()
			.map(x -> x.getPrice())
			.reduce(BigDecimal.ZERO, BigDecimal::add);
		 return sum;
	}
}
