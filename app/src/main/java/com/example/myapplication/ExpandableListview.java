package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ExpandableListview extends BaseExpandableListAdapter {

    final private String[] groupinfo = {"我的好友", "陌生人", "黑名单"};
    final private String[][] childinfo = {
            {"贝克汉姆", "科比"}, {"奥巴马", "普京", "卡梅伦"}, {"宋小宝", "韩红", "黄渤"}
    };
    final private int[][] childicon = {
            {R.drawable.beckham, R.drawable.kobe},
            {R.drawable.obama, R.drawable.puti, R.drawable.cameron},
            {R.drawable.songxiaobao, R.drawable.hanhong, R.drawable.huangbo}
    };

    final private Context context;
    private LayoutInflater inflater;

    public ExpandableListview(Context context) {
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount() {
        return groupinfo.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childinfo[groupPosition].length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupinfo[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childinfo[groupPosition][childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = inflater.inflate(R.layout.group_item, null);
        GroupViewHolder groupViewHolder = new GroupViewHolder();
        groupViewHolder.groupname = convertView.findViewById(R.id.ELA_txtview_title);
        groupViewHolder.groupname.setText(getGroup(groupPosition).toString());
        groupViewHolder.groupcount = convertView.findViewById(R.id.ELA_txtview_count);
        groupViewHolder.groupcount.setText("(" + getChildrenCount(groupPosition) + ")");

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = inflater.inflate(R.layout.child_item, null);
        ChildViewHolder childViewHolder = new ChildViewHolder();
        childViewHolder.childicon = convertView.findViewById(R.id.ELA_img_pic);
        childViewHolder.childicon.setImageResource(childicon[groupPosition][childPosition]);
        childViewHolder.childicon.setPadding(50, 10, 10, 10);
        childViewHolder.childname = convertView.findViewById(R.id.ELA_txtview_names);
        childViewHolder.childname.setText(getChild(groupPosition, childPosition).toString());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private class GroupViewHolder {
        TextView groupname;
        TextView groupcount;
    }

    private class ChildViewHolder {
        ImageView childicon;
        TextView childname;
    }
}
