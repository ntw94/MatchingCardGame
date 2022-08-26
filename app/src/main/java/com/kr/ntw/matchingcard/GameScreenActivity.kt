package com.kr.ntw.matchingcard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Collections.shuffle

class GameScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_screen)

        val cardList = ArrayList<Card>()

        val cardString = intent.getStringExtra("size")
        Log.d("gameScreen",""+cardString)

        val s = cardString?.split("x")
        val s1 = Integer.parseInt(s.get(0))
        val s2 = Integer.parseInt(s.get(1))

        //다음 할 일은 카드에 지금 텍스트 대신에 이미지 파일 넣기 glide써서 끝

        for( i in 0 .. s1*s2){
            cardList.add(Card(false,""+i,i*2))
            cardList.add(Card(false,""+i,i*2+1))
        }

        //카드 셔플
        shuffle(cardList)

        val recyclerView:RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = GameScreenRecyclerViewAdapter(
                cardList,
                LayoutInflater.from(this)
        )
        val gridLayoutManger = GridLayoutManager(applicationContext,3)
        recyclerView.layoutManager = gridLayoutManger


    }
}

class Card(
        var isSelected:Boolean = false,
        val content:String,
        val id:Int
){
    lateinit var itemView: View
}

class GameScreenRecyclerViewAdapter(
        val cardList: ArrayList<Card>,
        val layoutInflater: LayoutInflater,
): RecyclerView.Adapter<GameScreenRecyclerViewAdapter.CardViewHolder>(){

    var curSelectedCardList:ArrayList<Card> = ArrayList()

    inner class CardViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val cardItem = itemView.findViewById<TextView>(R.id.item_card)

        init{
            itemView.setOnClickListener {
                //똑같은거 눌렀을때도 막아야되네 어떻게 막지?

                    if (curSelectedCardList.size == 1) {
                        if(curSelectedCardList.get(0).id !=
                                        cardList.get(adapterPosition).id) {

                            if (curSelectedCardList.get(0).content ==
                                    cardList.get(adapterPosition).content) {
                                itemView.visibility = View.INVISIBLE
                                curSelectedCardList.get(0).itemView.visibility = View.INVISIBLE
                            } else {
                                val preCard = curSelectedCardList.get(0).itemView.findViewById<TextView>(R.id.item_card)
                                preCard.setBackgroundResource(R.drawable.card_board)
                                cardItem.setBackgroundResource(R.drawable.card_board)
                            }
                            curSelectedCardList.get(0).isSelected = false
                            curSelectedCardList.removeAt(0)
                        }else{
                            val preCard = curSelectedCardList.get(0).itemView.findViewById<TextView>(R.id.item_card)
                            preCard.setBackgroundResource(R.drawable.card_board)
                            curSelectedCardList.get(0).isSelected = false
                            curSelectedCardList.removeAt(0)
                        }
                    } else {
                        if (cardList.get(adapterPosition).isSelected == false) {
                            cardItem.setBackgroundResource(R.drawable.card_selected)
                            cardList.get(adapterPosition).isSelected = true
                            cardList.get(adapterPosition).itemView = itemView
                            curSelectedCardList.add(cardList.get(adapterPosition)) // 저장
                        }else{

                        }
                    }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = layoutInflater.inflate(R.layout.card_layout_back,parent,false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.cardItem.text = cardList.get(position).content
    }

    override fun getItemCount(): Int {
        return cardList.size
    }


}