package com.example.monee

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.RotateAnimation
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet.Motion
import com.example.monee.databinding.FragmentInsertBinding
import com.example.monee.databinding.FragmentSpinnerBinding
import kotlinx.android.synthetic.main.fragment_spinner.*
import kotlinx.android.synthetic.main.fragment_spinner.view.*
import java.io.InterruptedIOException


class SpinnerFragment : Fragment(), Animation.AnimationListener {

    private var _binding: FragmentSpinnerBinding ?= null
    private val binding get() = _binding!!

    private var count = 0
    private var flag = false

    private var spinButton:ImageButton ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSpinnerBinding.inflate(inflater, container, false)
        /* return inflater.inflate(R.layout.fragment_spinner, container, false) */

        spinButton = binding.spinButton.findViewById(id)
        spinButton!!.setOnTouchListener(PowerTouchListener())
        intSpinner()

        return binding.root
    }

    val prizes = intArrayOf(1,2,3,4,5,6,7,8,9,10,11,12)
    private var mSpinDuration :Long = 0
    private var mSpinRevolution = 0f
    var targetImageView:ImageView? = null
    var points: TextView? = null
    var prizeText = "N/A"


    private fun intSpinner() {
        targetImageView = binding.imageViewSpinWheel.findViewById(id)
        points = binding.textViewPoints.findViewById(id)

    }

    fun startSpinner(){
        mSpinRevolution = 3600f
        mSpinDuration =5000

        if(count >= 30){
            mSpinDuration = 1000
            mSpinRevolution = (3600 * 2).toFloat()
        }
        if(count >= 60){
            mSpinDuration = 15000
            mSpinRevolution = (3600 * 3).toFloat()
        }

        val end = Math.floor(Math.random() * 3600).toInt()
        val numOfPrizes = prizes.size
        val degreesPerPrize = 360/numOfPrizes
        val shift = 0
        val prizeIndex = (shift + end) % numOfPrizes

        prizeText = "${prizes[prizeIndex]}"

        val rotateAnim = RotateAnimation(0f,mSpinRevolution + end,
            Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f)

        rotateAnim.interpolator = DecelerateInterpolator()
        rotateAnim.repeatCount = 0
        rotateAnim.duration = mSpinDuration
        rotateAnim.setAnimationListener(this)
        rotateAnim.fillAfter =true
        targetImageView!!.startAnimation(rotateAnim)


    }

    override fun onAnimationStart(p0: Animation?) {
        textViewPoints!!.text = "Spinning....."
    }

    override fun onAnimationEnd(p0: Animation?) {
        textViewPoints.text = prizeText
    }

    override fun onAnimationRepeat(p0: Animation?) {}

    private inner class PowerTouchListener: View.OnTouchListener{
        override fun onTouch(p0: View?, motionEvent: MotionEvent?): Boolean {

            when(motionEvent!!.action){
                MotionEvent.ACTION_DOWN -> {
                    flag = true
                    count = 0
                    Thread{
                        while(flag){
                            count++
                            if(count == 100){
                                try{
                                    Thread.sleep(100)
                                }catch (e : InterruptedException){
                                    e.printStackTrace()
                                }
                                count = 0
                            }
                            try{
                                Thread.sleep(10)
                            }catch(e : InterruptedException){
                                e.printStackTrace()
                            }
                        }
                    }
                    return true
                }
                MotionEvent.ACTION_UP ->{
                    flag = false
                    startSpinner()
                    return false
                }
            }


            return false
        }
    }


}