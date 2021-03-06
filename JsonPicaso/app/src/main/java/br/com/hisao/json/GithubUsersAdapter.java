package br.com.hisao.json;

/**
 * Created by vinicius on 9/24/16.
 */

        import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

        import com.squareup.picasso.Picasso;

        import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by viniciushisao
 */
public class GithubUsersAdapter extends BaseAdapter{

    private JSONArray jsonArray;
    private Activity activity;

    public GithubUsersAdapter(JSONArray jsonArray, Activity activity){
        this.jsonArray = jsonArray;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return jsonArray.length();
    }

    @Override
    public Object getItem(int i) {
        try {
            return jsonArray.get(i);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private String projectName = null;
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (activity).getLayoutInflater();
        View row = inflater.inflate(R.layout.githubuser_item, viewGroup, false);

        final TextView txvProject = (TextView) row.findViewById(R.id.txv_project);
        final ImageView imvAvatar = (ImageView) row.findViewById(R.id.imv_avatar);
        final ProgressBar spnLoadingImage = (ProgressBar) row.findViewById(R.id.spn_imageloading);
        JSONObject jsonObject;
        String avatarUrl = null;
        try {
            jsonObject = this.jsonArray.getJSONObject(i);
            projectName = jsonObject.getString("name");
            avatarUrl = jsonObject.getJSONObject("owner").getString("avatar_url");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Picasso.with(activity.getApplicationContext()).load(avatarUrl).centerCrop().into(imvAvatar);

//        AsyncTask<String, Void, Bitmap> downloadImage = new AsyncTask<String, Void, Bitmap>(){
//
//            @Override
//            protected void onPreExecute() {
//                super.onPreExecute();
//                imvAvatar.setVisibility(View.GONE);
//                spnLoadingImage.setVisibility(View.VISIBLE);
//            }
//
//            @Override
//            protected void onPostExecute(Bitmap bitmap) {
//                imvAvatar.setImageBitmap(bitmap);
//                txvProject.setText(projectName);
//
//                imvAvatar.setVisibility(View.VISIBLE);
//                spnLoadingImage.setVisibility(View.GONE);
//            }
//
//            @Override
//            protected Bitmap doInBackground(String... strings) {
//                return DownloadUtil.downloadBitmap(strings[0]);
//            }
//        };
//
//        downloadImage.execute(avatarUrl,avatarUrl,avatarUrl,avatarUrl,avatarUrl,avatarUrl);

        return row;
    }
}

