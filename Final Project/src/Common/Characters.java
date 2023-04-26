package Common;

public class Characters {
    public static class ThickBorder implements Border {
        public static final char TOP_LEFT = '┏';
        public static final char TOP = '━';
        public static final char TOP_RIGHT = '┓';
        public static final char SIDE = '┃';
    }
    
    public interface Border {
        char TOP_LEFT = 0;
        char TOP = 0;
        char TOP_RIGHT = 0;
        char SIDE = 0;
        char BOTTOM_LEFT = 0;
        char BOTTOM = 0;
        char BOTTOM_RIGHT = 0;
    } 
}