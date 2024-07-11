package isga.artiweb.tourismapp.exception;

import isga.artiweb.tourismapp.entities.TourTypeEnum;

public class ResourceNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String mesg, String id, Integer userId){
        super(mesg);
    }

    public ResourceNotFoundException(String mesg, String email, String emailId){
        super(mesg);
    }

    public ResourceNotFoundException(String mesg, String email, TourTypeEnum tourType){
        super(mesg);
    }
}
