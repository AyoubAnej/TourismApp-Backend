package isga.artiweb.tourismapp.dto;

import java.time.LocalDateTime;

public class ApiResponse {
    private String message;
    private LocalDateTime timestamp;
    private Boolean status;

    public ApiResponse(String message, boolean bool) {
        super();
        this.message = message;
        this.status = bool;
        this.timestamp = LocalDateTime.now();
    }
}
