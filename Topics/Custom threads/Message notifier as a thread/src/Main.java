
class MessageNotifier extends Thread {
    private String msg;
    private int repeats;

    public MessageNotifier(String msg, int repeats) {
        super();
        this.msg = msg;
        this.repeats = repeats;
    }

    @Override
    public void run() {
        for (int i = 0; i < repeats; i++) {
            System.out.println(msg);
        }
    }
}
