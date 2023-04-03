package illidan.model;

import lombok.Data;

@Data
public class BaseApiResult {
    private boolean isSuccess;
    private String message;
}
