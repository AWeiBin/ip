package akari.expression;

public class ExpressionHandler {

    private static String chatbotExpression;

    public static String getExpression() {
        return chatbotExpression;
    }

    public static void setExpression(Expression expression) {
        switch (expression) {
        case GREET:
            chatbotExpression = "o(*￣▽￣*)ブ";
            break;
        case BYE:
            chatbotExpression = "ヾ(￣▽￣) Bye~Bye~";
            break;
        case ECHO:
            chatbotExpression = "o(〃＾▽＾〃)o 🕬";
            break;
        case LAUGH:
            chatbotExpression = "(￣y▽￣)╭ Ohohoho.....";
            break;
        case FOCUS:
            chatbotExpression = "໒(◔ᴗ◔)७✎▤";
            break;
        case ANGRY:
            chatbotExpression = "(╯°□°）╯︵ ┻\u2501┻";
            break;
        case SAD:
            chatbotExpression = "( •᷄ _ •᷅ ）";
            break;
        case PROUD:
            chatbotExpression = "ᕦ(ò_óˇ)ᕤ";
            break;
        default:
            chatbotExpression = "._.";
        }
    }
}
