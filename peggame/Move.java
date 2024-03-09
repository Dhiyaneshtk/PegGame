package peggame;

public class Move{
    private Location from;
    private Location to;

    /**
     * Creates a Move object with position and destination locations
     * @param from the current position of the peg
     * @param to the destination of the peg
     */
    public Move(Location from, Location to){
        this.from = from;
        this.to = to;
    }

    public Location getFrom() {
        return this.from;
    }

    public Location getTo() {
        return this.to;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Move)){return false;}
        Move other = (Move) (obj);
        return (this.getFrom().equals(other.getFrom()) && this.getTo().equals(other.getTo()));
    }

    @Override
    public String toString() {
        return ("move " + this.getFrom() + " " + this.getTo());
    }
}