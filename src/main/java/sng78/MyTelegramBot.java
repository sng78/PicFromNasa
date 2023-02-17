package sng78;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.IOException;

public class MyTelegramBot extends TelegramLongPollingBot {
    private static final String BOT_TOKEN =
            "6169589321:AAHXLTMxUAZcCPEzoru_WO7-Zfc4-N81TTk";
    private static final String BOT_USERNAME = "NasaNewBot_bot";
    public static final String URI = "https://api.nasa.gov/planetary/apod?api_key=" +
            "KVhqJ7nkVobbbRPzpbIotmc2Za4dO7E5ndRRlmpG";
    public static long chat_id;

    public MyTelegramBot() throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(this);
    }

    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            chat_id = update.getMessage().getChatId();
            switch (update.getMessage().getText()) {
                case "/help":
                    sendMessage("Привет, я бот NASA! Я высылаю ссылки " +
                            "на картинки по запросу. Картинки " +
                            "на сайте NASA обновляются раз в сутки" +
                            "\n\n" + "Список команд: " +
                            "\n" + "/give - получить картинку с сайта Nasa");
                    break;
                case "/give":
                    try {
                        sendMessage(Utils.getURL(URI));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                default:
                    sendMessage("Я не понимаю, неизвестная команда :(");
            }
        }
    }

    private void sendMessage(String messageText) {
        SendMessage message = new SendMessage();
        message.setChatId(chat_id);
        message.setText(messageText);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
