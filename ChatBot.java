import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatBot {

    private final Map<String, String[]> patterns;

    public ChatBot() {
        patterns = new HashMap<>();
        patterns.put("my name is (.*)", new String[]{"Hello %1, how can I help you today?"});
        patterns.put("what is your name?", new String[]{"My name is ChatBot and I'm here to assist you."});
        patterns.put("how are you ?", new String[]{"I'm doing good\nHow about you?"});
        patterns.put("sorry (.*)", new String[]{"It's alright, no problem."});
        patterns.put("hi|hey|hello", new String[]{"Hello", "Hey there"});
        patterns.put("(.*) age?", new String[]{"I'm a computer program, I don't have an age!"});
        patterns.put("(.*) (location|city) ?", new String[]{"I am from a virtual world."});
        patterns.put("what (.*) want ?", new String[]{"Make me an offer I can't refuse!"});
        patterns.put("(.*) created you ?", new String[]{"I was created by a software developer."});
        patterns.put("bye", new String[]{"Bye! Take care.", "Have a great day!"});
    }

    public void start() {
        System.out.println("Hi, I'm ChatBot! How can I assist you today?");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine().toLowerCase();
            boolean foundPattern = false;

            for (Map.Entry<String, String[]> entry : patterns.entrySet()) {
                String pattern = entry.getKey();
                String[] responses = entry.getValue();

                Matcher matcher = Pattern.compile(pattern).matcher(input);
                if (matcher.matches()) {
                    foundPattern = true;
                    String response = responses[(int) (Math.random() * responses.length)];
                    for (int i = 1; i <= matcher.groupCount(); i++) {
                        response = response.replace("%" + i, matcher.group(i));
                    }
                    System.out.println(response);
                    break;
                }
            }

            if (!foundPattern) {
                System.out.println("Sorry, I don't understand that.");
            }

            if (input.contains("bye")) {
                break;
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        ChatBot chatbot = new ChatBot();
        chatbot.start();
    }
}
