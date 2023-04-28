import java.util.Random;

public class generate_password {
    public static void main(String[] args) {
        System.out.print("Enter password length: ");
        int length = Integer.parseInt(System.console().readLine());
        String password = generatePassword(length);
        System.out.println("Random Password: " + password);
    }

    public static String generatePassword(int length) {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()_+";
        Random random = new Random();
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(chars.length());
            password.append(chars.charAt(index));
        }
        return password.toString();
    }
}
