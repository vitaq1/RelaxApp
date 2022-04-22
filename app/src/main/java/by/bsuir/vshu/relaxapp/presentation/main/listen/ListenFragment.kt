package by.bsuir.vshu.relaxapp.presentation.main.listen

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import by.bsuir.vshu.relaxapp.R
import by.bsuir.vshu.relaxapp.presentation.main.SharedViewModel
import java.text.SimpleDateFormat
import java.util.*

class ListenFragment : Fragment() {

    private val model by activityViewModels<SharedViewModel>()

    private lateinit var volumeBar: SeekBar
    private lateinit var positionBar: SeekBar
    private lateinit var playBtn: Button
    private lateinit var elapsedTimeLabel: TextView
    private lateinit var remainingTimeLabel: TextView


    private lateinit var mp: MediaPlayer
    private var totalTime: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_listen, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        if (SimpleDateFormat("HH", Locale.US).format(Calendar.getInstance().time).toString()
                .toInt() < 24
        )
            mp = MediaPlayer.create(requireContext(), R.raw.music_day)
        else if(SimpleDateFormat("HH", Locale.US).format(Calendar.getInstance().time).toString()
            .toInt() < 16
        )
            mp = MediaPlayer.create(requireContext(), R.raw.music_night)
        else if(SimpleDateFormat("HH", Locale.US).format(Calendar.getInstance().time).toString()
                .toInt() < 8
        )
            mp = MediaPlayer.create(requireContext(), R.raw.music_fun)

        mp.isLooping = true
        mp.setVolume(0.5f, 0.5f)
        totalTime = mp.duration

        // Volume Bar
        volumeBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekbar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    if (fromUser) {
                        val volumeNum = progress / 100.0f
                        mp.setVolume(volumeNum, volumeNum)
                    }
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {
                }
                override fun onStopTrackingTouch(p0: SeekBar?) {
                }
            }
        )

        // Position Bar
        positionBar.max = totalTime
        positionBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    if (fromUser) {
                        mp.seekTo(progress)
                    }
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {
                }

                override fun onStopTrackingTouch(p0: SeekBar?) {
                }
            }
        )

        // Thread
        Thread(Runnable {
            while (mp != null) {
                try {
                    var msg = Message()
                    msg.what = mp.currentPosition
                    handler.sendMessage(msg)
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                }
            }
        }).start()
    }

    @SuppressLint("HandlerLeak")
    var handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            var currentPosition = msg.what

            // Update positionBar
            positionBar.progress = currentPosition

            // Update Labels
            var elapsedTime = createTimeLabel(currentPosition)
            elapsedTimeLabel.text = elapsedTime

            var remainingTime = createTimeLabel(totalTime - currentPosition)
            remainingTimeLabel.text = "-$remainingTime"
        }
    }

    fun createTimeLabel(time: Int): String {
        var timeLabel = ""
        var min = time / 1000 / 60
        var sec = time / 1000 % 60

        timeLabel = "$min:"
        if (sec < 10) timeLabel += "0"
        timeLabel += sec

        return timeLabel
    }

    fun playBtnClick() {

        if (mp.isPlaying) {
            // Stop
            mp.pause()
            playBtn.setBackgroundResource(R.drawable.play)

        } else {
            // Start
            mp.start()
            playBtn.setBackgroundResource(R.drawable.stop)
        }
    }

    private fun initViews() {
        volumeBar = requireView().findViewById(R.id.volumeBar)
        positionBar = requireView().findViewById(R.id.positionBar)
        elapsedTimeLabel = requireView().findViewById(R.id.elapsedTimeLabel)
        remainingTimeLabel = requireView().findViewById(R.id.remainingTimeLabel)
        playBtn = requireView().findViewById(R.id.playBtn)
        playBtn.setOnClickListener { playBtnClick() }
    }

}