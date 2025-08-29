public class ExpressionHandler {

    private static String chatbotExpression;

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
            chatbotExpression = "(╯°□°）╯︵ ┻━┻";
            break;
        case SAD:
            chatbotExpression = "( •᷄ _ •᷅ ）";
            break;
        default:
            chatbotExpression = "._.";
        }
    }

    public static String getExpression() {
        return chatbotExpression;
    }
}
