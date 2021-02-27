package com.example.myapplication


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class OnBoardingSlideAdapter(private val onBoardingSlides: List<OnBoardingSlide>) :
    RecyclerView.Adapter<OnBoardingSlideAdapter.OnBoardingSlideViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingSlideViewHolder {
        return OnBoardingSlideViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.slide_item_container,
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: OnBoardingSlideViewHolder, position: Int) {
        holder.bind(onBoardingSlides[position])
    }

    override fun getItemCount(): Int {
        return onBoardingSlides.size
    }


    inner class OnBoardingSlideViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textTitle = view.findViewById<TextView>(R.id.textTitle)
        private val textDescription = view.findViewWithTag<TextView>(R.id.textDescription)
        private val imageIcon = view.findViewById<ImageView>(R.id.imageSlideIcon)

        fun bind(onBoardingSlide: OnBoardingSlide) {
            textTitle.text = onBoardingSlide.title
            textDescription.text = onBoardingSlide.description
            imageIcon.setImageResource(onBoardingSlide.icon)
        }
    }


}