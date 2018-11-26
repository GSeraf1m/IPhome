package com.app.saintjimmy.iphome;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.app.saintjimmy.iphome.Model.Ingrediente;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;

public class ExpandableAdapter extends BaseExpandableListAdapter {
    private List<String> listCategoria;
    private HashMap<String,List<Ingrediente>> listIngrediente;
    private LayoutInflater inflater;
    public HashMap<Integer, boolean[]> mChildCheckStates;
    public HashMap<Integer, boolean[]> mChildCheckFavoritos;

    public ExpandableAdapter(Context context, List<String> listCategoria, HashMap<String, List<Ingrediente>> listIngrediente) {
        this.listCategoria = listCategoria;
        this.listIngrediente = listIngrediente;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mChildCheckStates = new HashMap<Integer, boolean[]>();
        mChildCheckFavoritos = new HashMap<Integer, boolean[]>();
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
        final int mCategoriaPosicao=i;
        final int mCategoriaItem=i1;
        ViewHolderItem holder;
        Ingrediente ingrediente = (Ingrediente) getChild(mCategoriaPosicao,mCategoriaItem);
        if (view == null){
            view = inflater.inflate(R.layout.item_list_view,null);
            holder = new ViewHolderItem();
            holder.tvItem = view.findViewById(R.id.tvItem);
            holder.cbItem = view.findViewById(R.id.cbItem);
            holder.cbFavoritoItem = view.findViewById(R.id.cbFavoritoItem);
            view.setTag(R.layout.item_list_view,holder);
        }else{
            holder = (ViewHolderItem) view.getTag(R.layout.item_list_view);
        }
        holder.tvItem.setText(ingrediente.getNome());
        holder.cbItem.setOnCheckedChangeListener(null);
        holder.cbFavoritoItem.setOnCheckedChangeListener(null);

        //Saber se o checkbox do ingrediente ta marcado ou não
        if(mChildCheckStates.containsKey(mCategoriaPosicao)){
            boolean getChecked[]=mChildCheckStates.get(mCategoriaPosicao);
            holder.cbItem.setChecked(getChecked[mCategoriaItem]);
        }else{
            boolean getChecked[]=new boolean[getChildrenCount(mCategoriaPosicao)];
            mChildCheckStates.put(mCategoriaPosicao,getChecked);
            holder.cbItem.setChecked(false);
        }

        //Saber se o Checkbox favoritos está marcado ou não
        if(mChildCheckFavoritos.containsKey(mCategoriaPosicao)){
            boolean getChecked[]=mChildCheckFavoritos.get(mCategoriaPosicao);
            holder.cbFavoritoItem.setChecked(getChecked[mCategoriaItem]);
        }else{
            boolean getChecked[] = new boolean[getChildrenCount(mCategoriaPosicao)];
            mChildCheckFavoritos.put(mCategoriaPosicao,getChecked);
            holder.cbFavoritoItem.setChecked(false);
        }

        //Marcando o checkbox do ingrediente
        holder.cbItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    boolean getChecked[]= mChildCheckStates.get(mCategoriaPosicao);
                    getChecked[mCategoriaItem]=isChecked;
                    mChildCheckStates.put(mCategoriaPosicao,getChecked);
                }else{
                    boolean getChecked[]= mChildCheckStates.get(mCategoriaPosicao);
                    getChecked[mCategoriaItem]=isChecked;
                    mChildCheckStates.put(mCategoriaPosicao,getChecked);
                }
            }
        });

        //Marcando o checkbox favoritos
        holder.cbFavoritoItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    boolean getChecked[]= mChildCheckFavoritos.get(mCategoriaPosicao);
                    getChecked[mCategoriaItem]=isChecked;
                    mChildCheckFavoritos.put(mCategoriaPosicao,getChecked);
                }else{
                    boolean getChecked[]= mChildCheckFavoritos.get(mCategoriaPosicao);
                    getChecked[mCategoriaItem]=isChecked;
                    mChildCheckFavoritos.put(mCategoriaPosicao,getChecked);
                }
            }
        });
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

    class ViewHolderGroup {
        TextView tvGroup;
    }
    class ViewHolderItem{
        TextView tvItem;
        CheckBox cbItem;
        CheckBox cbFavoritoItem;
    }
}