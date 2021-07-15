public class Cell {
    
    private CellStates state;
    private Bag<Cell> adjCells;
    private int x;
    private int y;

    private boolean shouldSwitch;

    public Cell() {
        state = CellStates.DEAD;
        adjCells = new Bag<>();
    }

    public void check() {
        int liveNeighbors = 0;
        shouldSwitch = false;

        for (Cell c : adjCells) {
            if (c.getState().equals(CellStates.ALIVE)) liveNeighbors++;
        }

        if (state.equals(CellStates.ALIVE) && (liveNeighbors < 2 || liveNeighbors > 3)) {
            shouldSwitch = true;
        } else if (state.equals(CellStates.DEAD) && liveNeighbors == 3) {
            shouldSwitch = true;
        } 
    }

    public CellStates tick() {
        if (shouldSwitch) {
            switchState();
        }
        return state;
    }

    public void setState(CellStates state) {
        this.state = state;
    }

    public void switchState() {
        if (state.equals(CellStates.ALIVE)) state = CellStates.DEAD;
        else state = CellStates.ALIVE;
    }

    public CellStates getState() {
        return state;
    }

    public void addAdjCell(Cell cell) {
        adjCells.add(cell);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
