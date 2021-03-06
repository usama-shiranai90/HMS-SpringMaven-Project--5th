package Java.HMS.Data;

//@Service
public class Room extends Ward {

    private int roomno;
    private String status;
    private boolean occupied;

    public Room(int wardno, String wardname,  int numberofbeds, int roomno, String status) {
        super(wardno, wardname,  numberofbeds);
        this.roomno = roomno;
        this.status = status;
        if (status.equalsIgnoreCase("vacant")) occupied=true;
        else if (status.equalsIgnoreCase("empty")) occupied =false;
    }

    public Room(int wardno, String wardname, int floorno, int extensiono, int numberofbeds, int roomno, boolean occupied) {
        super(wardno, wardname, floorno, extensiono, numberofbeds);
        this.roomno = roomno;
        this.occupied = occupied;
    }



    public int getRoomno() {
        return roomno;
    }

    public void setRoomno(int roomno) {
        this.roomno = roomno;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    /*    public List<Room> RoomList() {
        List<Room> roomList = new ArrayList<>();
        try {
            DatabaseConnection instance = DatabaseConnection.getInstance();
            Connection connection = instance.getConnection();
            Statement statement = connection.createStatement();
            String query = "select RoomID, BedID, `Bed status` from room;";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                roomList.add(new Room(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getString(3)

                ));
            }

            return roomList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }*/



}

