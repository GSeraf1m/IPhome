package com.app.saintjimmy.iphome;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.app.saintjimmy.iphome.Model.Ingrediente;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;

public class ExpandableAdapter extends BaseExpandableListAdapter {
    private List<String> listCategoria;
    private HashMap<String,List<Ingrediente>> listIngrediente;
    private LayoutInflater inflater;

    public ExpandableAdapter(Context context, List<String> listCategoria, HashMap<String, List<Ingrediente>> listIngrediente) {
        this.listCategoria = listCategoria;
        this.listIngrediente = listIngrediente;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount() {
        return listCategoria.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return listIngrediente.get(listCategoria.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return listCategoria.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return listIngrediente.get(listCategoria.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return listIngrediente.get(listCategoria.get(i)).get(i1).getId();
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        ViewHolderGroup holder;
        if (view == null){
            view = inflater.inflate(R.layout.group_list_view,null);
            holder = new ViewHolderGroup();
            view.setTag(holder);

            holder.tvGroup = view.findViewById(R.id.tvGroup);
        }else{
            holder = (ViewHolderGroup) view.getTag();
        }
        holder.tvGroup.setText(listCategoria.get(i));
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        ViewHolderItem holder;
        Ingrediente ingrediente = (Ingrediente) getChild(i,i1);
        if (view == null){
            view = inflater.inflate(R.layout.group_list_view,null);
            holder = new ViewHolderItem();
            view.setTag(holder);

            holder.tvItem = view.findViewById(R.id.tvItem);
        }else{
            holder = (ViewHolderItem) view.getTag();
        }
        holder.tvItem.setText(ingrediente.getNome());
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    class ViewHolderGroup {
        TextView tvGroup;
    }
    class ViewHolderItem{
        TextView tvItem;
    }
}
