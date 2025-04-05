package pojo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Entity {
    @Builder.Default
    private Addition addition = Addition.builder().build();

    @Builder.Default
    private List<Integer> important_numbers = List.of();

    @Builder.Default
    private String title = "";

    @Builder.Default
    private boolean verified = false;

    @Data
    @Builder
    public static class Addition {
        @Builder.Default
        private String additional_info = "";

        @Builder.Default
        private int additional_number = 0;
    }
}