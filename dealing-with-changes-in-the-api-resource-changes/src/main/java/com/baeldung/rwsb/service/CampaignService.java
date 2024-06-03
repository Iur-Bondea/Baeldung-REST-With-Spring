package com.baeldung.rwsb.service;

import java.util.List;
import java.util.Optional;

import com.baeldung.rwsb.domain.model.Campaign;

public interface CampaignService {

    List<Campaign> findCampaigns();

    Optional<Campaign> findById(String id);

    Campaign save(Campaign campaign);

    Optional<Campaign> updateCampaign(String id, Campaign campaign);
}
