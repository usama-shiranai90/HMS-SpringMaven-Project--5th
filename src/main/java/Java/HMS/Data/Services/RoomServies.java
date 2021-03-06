package Java.HMS.Data.Services;

import Java.HMS.Data.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoomServies {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Room> findAll() {
        return jdbcTemplate.query(
                "select ward.wardno , wardname , noofbeds, roomno , Status from ward join room r on ward.wardno = r.WardNo;",
                (rs, rowNum) -> new Room(  rs.getInt(1),rs.getString(2), rs.getInt(3)  ,rs.getInt(4) , rs.getString(5)));
    }

    public void removeroom(Room room) {
        jdbcTemplate.update(
                "UPDATE room set status ='Empty', isOccupied=0 where isOccupied is TRUE and roomno = ? and WardNo = ? ",  room.getRoomno(),room.getWardno()
        );
    }



}
