package hexlet.code;

public final class Entry {
    private String name;
    private String status;
    private Object firstValue;
    private Object secondValue;

    public Entry(String newName, String newStatus, Object newFirstValue, Object newSecondValue) {
        this.name = newName;
        this.status = newStatus;
        this.firstValue = newFirstValue;
        this.secondValue = newSecondValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String newStatus) {
        this.status = newStatus;
    }

    public Object getFirstValue() {
        return firstValue;
    }

    public void setFirstValue(Object newFirstValue) {
        this.firstValue = newFirstValue;
    }

    public Object getSecondValue() {
        return secondValue;
    }

    public void setSecondValue(Object newSecondValue) {
        this.secondValue = newSecondValue;
    }
}
