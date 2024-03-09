package peggame;

public class Location {
    private int row;
    private int col;

   /**
     * 
     * @param row initializes row coord of the location
     * @param col initializes the column coord of the location
     */
    public Location(int row, int col){
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Location)) {return false;}
        Location other = (Location) (obj);
        return (this.getRow() == other.getRow() && this.getCol() == other.getCol());
    }

    @Override
    public String toString() {
        return "r" + this.getRow() + " c" + this.getCol();
    }
}
