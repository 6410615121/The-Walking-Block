public class Server {
    public static void main(String[] args) {
        Snake snake = new Snake(new int[]{0,0});
        snake.move("up");
        snake.eat(new Apple(new int[]{0,0}));
    }
}
