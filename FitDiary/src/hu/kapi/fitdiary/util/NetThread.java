package hu.kapi.fitdiary.util;

import hu.kapi.fitdiary.util.InternetConnection;
import hu.kapi.fitdiary.util.Session;
import android.content.Context;

public class NetThread extends Thread {
    public Context context;

    public NetThread(Context context, Runnable r) {
        super(r);
        this.context = context;
    }

    @Override
    public synchronized void start() {
        if (!InternetConnection.isOnline(context)) {
            Session.getInstance().dismissProgressDialog();
            new ErrorToast(context, "Nincs internetkapcsolat!").show();
            return;
        }
        super.start();
    }
}
