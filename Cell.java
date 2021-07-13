public class Cell {
    
    private CellStates state;
    private Bag<Cell> adjCells;

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

    public void tick() {
        if (shouldSwitch) {
            if (state.equals(CellStates.ALIVE)) state = CellStates.DEAD;
            else state = CellStates.ALIVE;
        }
    }

    public void setState(CellStates state) {
        this.state = state;
    }

    public CellStates getState() {
        return state;
    }

    public void addAdjCell(Cell cell) {
        adjCells.add(cell);
    }
}
