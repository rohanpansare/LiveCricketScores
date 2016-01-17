package rohan.com.livecricketscores1;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Rohan Pansare on 1/15/2016.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ScoreViewHolder>{

    List<LiveScoreData> mScoreDatas;

    RVAdapter(List<LiveScoreData> mScoreDatas){
        this.mScoreDatas = mScoreDatas;
    }
    @Override
    public ScoreViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.content_main, viewGroup, false);
        ScoreViewHolder pvh = new ScoreViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ScoreViewHolder holder, int i) {
        holder.matchTitle.setText(mScoreDatas.get(i).getTeam1Name());
        holder.matchId.setText(mScoreDatas.get(i).getTeam2Name());
      //  personViewHolder.personPhoto.setImageResource(persons.get(i).photoId);
    }

    @Override
    public int getItemCount() {
        return mScoreDatas.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class ScoreViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView matchTitle;
        TextView matchId;
        ImageView personPhoto;

        ScoreViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            matchTitle = (TextView) itemView.findViewById(R.id.match_title);
            matchId = (TextView) itemView.findViewById(R.id.Score);
           // personPhoto = (ImageView) itemView.findViewById(R.id.person_photo);
        }



    }
}
