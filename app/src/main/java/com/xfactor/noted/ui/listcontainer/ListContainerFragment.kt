package com.xfactor.noted.ui.listcontainer

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xfactor.noted.*
import com.xfactor.noted.databinding.FragmentListcontainerBinding
import com.xfactor.noted.databinding.FragmentListitemBinding

private lateinit var statusText : TextView
private lateinit var listContainerBinding: FragmentListcontainerBinding

fun updateStatus() {
    if(ListsToCompare.size == 0)  return
    var status = "Selected: ".plus(ListsToCompare[0].title)
    if(ListsToCompare.size == 2) {
        status = status.plus(", ").plus(ListsToCompare[1].title)
    }
    statusText.text = status
}

class ListContainerFragment : Fragment() {

    private var adapter = ListsAdapter(Lists)

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        listContainerBinding = FragmentListcontainerBinding.inflate(inflater, container, false)
        listContainerBinding.allLists.layoutManager = GridLayoutManager(context, 2)
        listContainerBinding.allLists.adapter = adapter
        statusText = listContainerBinding.status
        updateStatus()
        return listContainerBinding.root
    }

}
class ListsAdapter(private val dataSet: MutableList<ListItem>) :
    RecyclerView.Adapter<ListsAdapter.ViewHolder>() {

    class ViewHolder(listItemBinding: FragmentListitemBinding) : RecyclerView.ViewHolder(listItemBinding.root) {
        val listItem: LinearLayout = listItemBinding.listitem
        val listTitle: TextView = listItemBinding.listTitle
        val listElements: TextView = listItemBinding.listElements
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.listItem.setOnClickListener {
            if(ListsToCompare.size > 1) {
                ListsToCompare.clear()
            }
            ListsToCompare.add(dataSet[position])
            updateStatus()
        }
        val title = viewHolder.listTitle
        title.text = dataSet[position].title
        title.paintFlags = title.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        viewHolder.listElements.text = getSubItems(dataSet[position])
        val visibility: Int = viewHolder.listItem.visibility
        viewHolder.listItem.visibility = View.GONE
        viewHolder.listItem.visibility = visibility
    }

    override fun getItemCount():Int {
        return dataSet.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(FragmentListitemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

}