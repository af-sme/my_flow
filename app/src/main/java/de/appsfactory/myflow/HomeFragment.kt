package de.appsfactory.myflow

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val viewModel: HomeViewModel by viewModels()

    private lateinit var settingsButton: MaterialButton
    private lateinit var counterButton: MaterialButton
    private lateinit var coldFlowTextView: MaterialTextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("MY_FLOW", "HomeFragment onViewCreated")
        super.onViewCreated(view, savedInstanceState)

        settingsButton = view.findViewById(R.id.settingsButton)
        counterButton = view.findViewById(R.id.counterButton)
        coldFlowTextView = view.findViewById(R.id.coldFlowTextView)

        counterButton.text = getString(R.string.counter, 0)

        settingsButton.setOnClickListener {
            parentFragmentManager.commit {
                addToBackStack(null)
                replace(R.id.container, SettingsFragment.newInstance())
            }
        }
        counterButton.setOnClickListener { viewModel.onCounter() }

        viewModel.hotData.observe(viewLifecycleOwner, {
            counterButton.text = getString(R.string.counter, it)
        })

        viewModel.coldData.observe(viewLifecycleOwner, {
            val s: String = coldFlowTextView.text.toString()
            coldFlowTextView.text = "$s$it "
        })
    }

}