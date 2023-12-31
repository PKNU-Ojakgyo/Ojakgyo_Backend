package Ojakgyo.com.example.Ojakgyo.dto;

import Ojakgyo.com.example.Ojakgyo.domain.DealerDealListInterface;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class SearchDealerResponse {
    private Long dealerId;
    private String email;
    private String name;
    private String phone;
    private List<DealerDealList> dealLists;
}
