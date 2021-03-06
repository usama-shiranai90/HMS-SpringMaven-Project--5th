package Observer;

import java.util.ArrayList;
import java.util.List;

public interface Publisher {

    List<Subscriber> eventSubscriber = new ArrayList<>();
    List<Subscriber> patientappointmentSubsciber = new ArrayList<>();

    public void eventnotify();

    public void patientappointmentnotify(List<Subscriber> patientappointmentSubsciber);

}
