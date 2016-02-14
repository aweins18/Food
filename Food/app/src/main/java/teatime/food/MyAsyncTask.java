package teatime.food;


import android.content.Context;
import android.os.AsyncTask;

public class MyAsyncTask extends AsyncTask
{

    private Context context;

    public MyAsyncTask(Context context) {  // can take other params if needed
        this.context = context;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        return null;
    }

    // Add your AsyncTask methods and logic
//you can use your context variable in onPostExecute() to manipulate activity UI
}