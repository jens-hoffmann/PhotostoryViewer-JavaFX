package view;

public class TextView {
    private StringBuilder text;

    public TextView() {
        this.text = new StringBuilder();
    }

    public void addLine(String line) {
        text.append(line).append(System.lineSeparator());
    }

    public String getView() {
        return this.text.toString();
    }
}
