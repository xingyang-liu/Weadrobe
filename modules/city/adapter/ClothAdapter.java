package com.xiecc.Weadrobe.modules.city.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import butterknife.Bind;
import com.xiecc.Weadrobe.R;
import com.xiecc.Weadrobe.base.BaseViewHolder;
import com.xiecc.Weadrobe.component.AnimRecyclerViewAdapter;
import com.xiecc.Weadrobe.modules.city.domain.Clothes;

import java.util.ArrayList;

public class ClothAdapter extends AnimRecyclerViewAdapter<ClothAdapter.CityViewHolder> {

    private Context mContext;
    private ArrayList<Clothes> mDataList;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public ClothAdapter(Context context, ArrayList<Clothes> dataList) {
        mContext = context;
        this.mDataList = dataList;
    }

    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CityViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_cloth, parent, false));
    }

    @Override
    public void onBindViewHolder(final CityViewHolder holder, final int position) {

        holder.bind(mDataList.get(position));
        holder.mCardView.setOnClickListener(v -> mOnItemClickListener.onItemClick(v, position));
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int pos);
    }

    class CityViewHolder extends BaseViewHolder<Clothes> {

        @Bind(R.id.item_cloth)
        TextView mItemCity;
        @Bind(R.id.checkBox)
        CheckBox mItemChecked;
        @Bind(R.id.cardView)
        CardView mCardView;

        public CityViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void bind(Clothes cloth) {
            mItemCity.setText(cloth.getName());
            mItemChecked.setChecked(cloth.ishave[cloth.ClothSort]);
        }
    }
}
