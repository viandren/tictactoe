package hu.viandren.tictactoe.enums;

public enum Result {
    WIN("win"),DEFEAT("defeat"),DRAW("draw"),EMPTY("");


    public final String text;

    Result(String text){
        this.text = text;
    }

    public static Result getByText(String text){
        for (Result value : Result.values()) {
            if (value.text.equals(text)){
                return value;
            }
        }
        throw new IllegalArgumentException();
    }
}
