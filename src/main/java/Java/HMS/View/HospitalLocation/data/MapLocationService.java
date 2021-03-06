package Java.HMS.View.HospitalLocation.data;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Demo backend that accepts up to 100 fishing spots. Data is shared with all
 * users.
 */
@Service
@ApplicationScope
public class MapLocationService {

    private List<MapLocation> spots = new ArrayList<MapLocation>();

    @PostConstruct
    private void init() {

        // Add some demo data

        spots.add(new MapLocation(33.59282801018376, 73.06576605571895, "Jinnah Memorial Hospital, Rawalpindi"));
        spots.add(new MapLocation(24.852337884048005, 67.04414826723263, "Jinnah Hospital Karachi"));
        spots.add(new MapLocation(31.484771566773684, 74.29738116917652, "Jinnah Hospital, Lahore"));
    }

    public List<MapLocation> getAll() {

        return Collections.unmodifiableList(spots);
    }

    public void addSpot(MapLocation spot) {

        // protect concurrent access since MapLocationService is a singleton
        synchronized (spots) {
            spots.add(spot);
            if (spots.size() > 100) {
                spots.remove(0);
            }
        }
    }
}
