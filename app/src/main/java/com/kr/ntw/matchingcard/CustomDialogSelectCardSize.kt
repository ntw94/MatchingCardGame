package com.kr.ntw.matchingcard

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * 인터페이스를 활용해서 데이터 전달하는 방법 찾기
 */
class CustomDialogSelectCardSize(context: Context) {
    private val dialog = Dialog(context)
    val cardSizeList = Array<String>(3,{""})
    fun showDialog(){
        dialog.setContentView(R.layout.dialog_card_size)
        dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.show()

        cardSizeList[0] = "3x4"
        cardSizeList[1] = "4x4"
        cardSizeList[2] = "4x5"

        val dialogRecyclerView = dialog.findViewById<RecyclerView>(R.id.dialog_recyclerView)
        dialogRecyclerView.adapter=
                DialogRecyclerViewAdapter(
                        cardSizeList,
                        LayoutInflater.from(dialog.context),
                        dialog.context,
                        dialog
                        )
    }

    /* 수정 필요 */
    private lateinit var onClickListener: OnDialogClickListener

    fun setOnClickListener(listener: OnDialogClickListener)
    {
        onClickListener = listener
    }

    interface OnDialogClickListener{
        fun onClicked(name:String)
    }

}

class DialogRecyclerViewAdapter(
        val cardSizeList:Array<String>,
        val layoutInflater: LayoutInflater,
        val context:Context,
        val dialog:Dialog
):RecyclerView.Adapter<DialogRecyclerViewAdapter.ViewHolder>()
{

    inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val cardSize = itemView.findViewById<TextView>(R.id.item_card_size)

        init{
            cardSize.setOnClickListener {
                dialog.dismiss()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = layoutInflater.inflate(R.layout.dialog_card_size_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.cardSize.text = cardSizeList.get(position)
    }

    override fun getItemCount(): Int {
        return cardSizeList.size
    }
}