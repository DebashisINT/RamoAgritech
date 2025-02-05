package com.breezefieldsalesramoagritech.features.mylearning
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.breezefieldsalesramoagritech.CustomStatic
import com.breezefieldsalesramoagritech.R
import com.breezefieldsalesramoagritech.app.AppDatabase
import com.breezefieldsalesramoagritech.app.NetworkConstant
import com.breezefieldsalesramoagritech.app.Pref
import com.breezefieldsalesramoagritech.app.domain.LMSNotiEntity
import com.breezefieldsalesramoagritech.app.types.FragType
import com.breezefieldsalesramoagritech.app.utils.AppUtils
import com.breezefieldsalesramoagritech.base.BaseResponse
import com.breezefieldsalesramoagritech.base.presentation.BaseActivity
import com.breezefieldsalesramoagritech.base.presentation.BaseFragment
import com.breezefieldsalesramoagritech.features.dashboard.presentation.DashboardActivity
import com.breezefieldsalesramoagritech.features.mylearning.SearchLmsLearningFrag.Companion.topic_id
import com.breezefieldsalesramoagritech.features.mylearning.SearchLmsLearningFrag.Companion.topic_name
import com.breezefieldsalesramoagritech.features.mylearning.apiCall.LMSRepoProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.google.android.material.progressindicator.LinearProgressIndicator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
class MyLearningFragment : BaseFragment(),OnClickListener {
    private lateinit var mContext: Context
    private lateinit var bottomNavigation: MeowBottomNavigation
    private lateinit var cv_lms_learner_space: CardView
    private lateinit var cv_lms_leaderboard: CardView
    private lateinit var ll_knowledgeHub: LinearLayout
    private lateinit var ll_myLearning: LinearLayout
    private lateinit var cv_frag_search_mylearning_root: CardView

    private lateinit var ll_lms_performance: LinearLayout
    private lateinit var iv_lms_performance: ImageView
    private lateinit var tv_lms_performance: TextView

    private lateinit var ll_lms_mylearning: LinearLayout
    private lateinit var iv_lms_mylearning: ImageView
    private lateinit var tv_lms_mylearning: TextView

    private lateinit var ll_lms_leaderboard: LinearLayout
    private lateinit var iv_lms_leaderboard: ImageView
    private lateinit var tv_lms_leaderboard: TextView

    private lateinit var ll_lms_knowledgehub: LinearLayout
    private lateinit var iv_lms_knowledgehub: ImageView
    private lateinit var tv_lms_knowledgehub: TextView
  //  private lateinit var sc_vw: ScrollView
    private lateinit var bottom_layout_lms_LL: LinearLayout
    private lateinit var tv_content: TextView
    private lateinit var tv_content_learning: TextView
    private lateinit var tv_content_knowledge: TextView
    lateinit var courseList: List<LmsSearchData>
    lateinit var courseListLearning: List<LmsSearchData>
    private lateinit var final_dataL: ArrayList<LarningList>

    private lateinit var cv_last_vid_root:CardView
    private lateinit var tv_lastVid_topicName:TextView
    private lateinit var tv_lastVid_contentName:TextView
    private lateinit var tv_lastVid_contentDesc:TextView
    private lateinit var iv_lastVid_thumbnail:ImageView
    private lateinit var lpi_frag_my_learning_last_content_parcentage: LinearProgressIndicator
    private lateinit var tv_frag_my_learning_last_content_prcntg: TextView
    private lateinit var tv_frag_my_learning_last_content_prcntg_status: TextView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater!!.inflate(R.layout.fragment_my_learning, container, false)
        initView(view)
       /* requireActivity().getOnBackPressedDispatcher()
            .addCallback(object : OnBackPressedCallback(true) {
                @Override
                override fun handleOnBackPressed() {

                }
            })*/
        return view
    }

    private fun initView(view: View) {
        //performance
        ll_lms_performance = view.findViewById(R.id.ll_lms_performance)
        iv_lms_performance = view.findViewById(R.id.iv_lms_performance)
        tv_lms_performance = view.findViewById(R.id.tv_lms_performance)

        //mylearning
        ll_lms_mylearning = view.findViewById(R.id.ll_lms_mylearning)
        iv_lms_mylearning = view.findViewById(R.id.iv_lms_mylearning)
        tv_lms_mylearning = view.findViewById(R.id.tv_lms_mylearning)

        //leaderboard
        ll_lms_leaderboard = view.findViewById(R.id.ll_lms_leaderboard)
        iv_lms_leaderboard = view.findViewById(R.id.iv_lms_leaderboard)
        tv_lms_leaderboard = view.findViewById(R.id.tv_lms_leaderboard)

        //knowledgehub
        ll_lms_knowledgehub = view.findViewById(R.id.ll_lms_knowledgehub)
        iv_lms_knowledgehub = view.findViewById(R.id.iv_lms_knowledgehub)
        tv_lms_knowledgehub = view.findViewById(R.id.tv_lms_knowledgehub)
      //  sc_vw = view.findViewById(R.id.sc_vw)

        iv_lms_leaderboard.setImageResource(R.drawable.leaderboard_new)
        iv_lms_performance.setImageResource(R.drawable.performance_black)
        iv_lms_mylearning.setImageResource(R.drawable.my_learning_black)
        iv_lms_knowledgehub.setImageResource(R.drawable.all_topic_black)

        iv_lms_leaderboard.setColorFilter(ContextCompat.getColor(mContext, R.color.black), android.graphics.PorterDuff.Mode.MULTIPLY)
        iv_lms_performance.setColorFilter(ContextCompat.getColor(mContext, R.color.black), android.graphics.PorterDuff.Mode.MULTIPLY)
        iv_lms_mylearning.setColorFilter(ContextCompat.getColor(mContext, R.color.black), android.graphics.PorterDuff.Mode.MULTIPLY)
        iv_lms_knowledgehub.setColorFilter(ContextCompat.getColor(mContext, R.color.black), android.graphics.PorterDuff.Mode.MULTIPLY)

        tv_lms_performance.setTextColor(getResources().getColor(R.color.black))
        tv_lms_mylearning.setTextColor(getResources().getColor(R.color.black))
        tv_lms_leaderboard.setTextColor(getResources().getColor(R.color.black))
        tv_lms_knowledgehub.setTextColor(getResources().getColor(R.color.black))

        cv_lms_learner_space = view.findViewById(R.id.cv_lms_learner_space)
        ll_knowledgeHub = view.findViewById(R.id.ll_frag_search_knowledge_hub_root)
        ll_myLearning = view.findViewById(R.id.ll_frag_search_mylearning_root)
        cv_frag_search_mylearning_root = view.findViewById(R.id.cv_frag_search_mylearning_root)
        cv_lms_leaderboard = view.findViewById(R.id.cv_lms_leaderboard)
        tv_content = view.findViewById(R.id.tv_content)
        tv_content_learning = view.findViewById(R.id.tv_content_learning)
        tv_content_knowledge = view.findViewById(R.id.tv_content_knowledge)
        bottom_layout_lms_LL = view.findViewById(R.id.bottom_layout_lms_LL)

        cv_last_vid_root = view.findViewById(R.id.cv_last_vid_root)
       // tv_lastVid_topicName = view.findViewById(R.id.tv_frag_my_learning_last_topic_name)
        tv_lastVid_contentName = view.findViewById(R.id.tv_frag_my_learning_last_content_name)
        tv_lastVid_contentDesc = view.findViewById(R.id.tv_frag_my_learning_last_content_desc)
        iv_lastVid_thumbnail = view.findViewById(R.id.iv_frag_my_learning_last_topic_img)
       // lpi_frag_my_learning_last_content_parcentage = view.findViewById(R.id.lpi_frag_my_learning_last_content_parcentage)
      //  tv_frag_my_learning_last_content_prcntg = view.findViewById(R.id.tv_frag_my_learning_last_content_prcntg)
      //  tv_frag_my_learning_last_content_prcntg_status = view.findViewById(R.id.tv_frag_my_learning_last_content_prcntg_status)

        try {
            if(!Pref.LastVideoPlay_TopicName.equals("")){
                cv_last_vid_root.visibility = View.VISIBLE

               // tv_lastVid_topicName.text = Pref.LastVideoPlay_TopicName
                tv_lastVid_contentName.text = Pref.LastVideoPlay_ContentName
                tv_lastVid_contentDesc.text = Pref.LastVideoPlay_ContentDesc
               // tv_frag_my_learning_last_content_prcntg.text = Pref.LastVideoPlay_ContentParcent


                /*if (!Pref.LastVideoPlay_ContentParcent.equals("")) {
                    lpi_frag_my_learning_last_content_parcentage.progress = Pref.LastVideoPlay_ContentParcent.toInt()
                    tv_frag_my_learning_last_content_prcntg.text = "${Pref.LastVideoPlay_ContentParcent}% complete"

                    if (Pref.LastVideoPlay_ContentParcent == "100") {
                        tv_frag_my_learning_last_content_prcntg_status.text = "Completed"
                        tv_frag_my_learning_last_content_prcntg_status.setTextColor(mContext.resources.getColor(R.color.approved_green))
                    } else {
                        tv_frag_my_learning_last_content_prcntg_status.text = "Continue"
                        tv_frag_my_learning_last_content_prcntg_status.setTextColor(mContext.resources.getColor(R.color.toolbar_lms))
                    }

                }else{
                    lpi_frag_my_learning_last_content_parcentage.progress = 0
                    tv_frag_my_learning_last_content_prcntg.text = "0 % complete"
                    tv_frag_my_learning_last_content_prcntg_status.text = "Not Started Yet"
                    tv_frag_my_learning_last_content_prcntg_status.setTextColor(mContext.resources.getColor(R.color.toolbar_lms))

                }*/

                if (!Pref.LastVideoPlay_BitmapURL.equals("")) {
                    Glide.with(mContext)
                        .load(Pref.LastVideoPlay_BitmapURL)
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_image).error(R.drawable.ic_image))
                        .into(iv_lastVid_thumbnail)
                }
                else{
                    iv_lastVid_thumbnail.setImageResource(R.drawable.ic_image)
                }
            }else{
                cv_last_vid_root.visibility = View.GONE
            }

            cv_last_vid_root.setOnClickListener {
                setHomeClickFalse()
                VideoPlayLMS.loadedFrom = "LMSDASHBOARD"
                CustomStatic.VideoPosition = Pref.LastVideoPlay_VidPosition.toInt()
                Pref.videoCompleteCount = "0"
                (mContext as DashboardActivity).loadFragment(FragType.VideoPlayLMS, true, Pref.LastVideoPlay_TopicID +"~"+ Pref.LastVideoPlay_TopicName/*+"~"+position*/)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }


        cv_lms_learner_space.setOnClickListener {
            setHomeClickFalse()
            (mContext as DashboardActivity).loadFragment(FragType.SearchLmsFrag, true, "")
        }

        ll_knowledgeHub.setOnClickListener(this)
        cv_lms_leaderboard.setOnClickListener(this)
        ll_lms_performance.setOnClickListener(this)
        ll_lms_mylearning.setOnClickListener(this)
        ll_lms_leaderboard.setOnClickListener(this)
        ll_lms_knowledgehub.setOnClickListener(this)
        ll_myLearning.setOnClickListener(this)
        cv_frag_search_mylearning_root.setOnClickListener(this)

        if (AppUtils.isOnline(mContext)) {
           // sc_vw.visibility =View.VISIBLE
            bottom_layout_lms_LL.visibility =View.VISIBLE
            getTopicLAssigened()
            getTopicLearningAssigened()
           // getMyLarningTopicListInfoAPI()
            getTopicL()
        }else{
          //  sc_vw.visibility =View.GONE
            bottom_layout_lms_LL.visibility =View.GONE
            (mContext as DashboardActivity).showSnackMessage(getString(R.string.no_internet))

        }

        Handler().postDelayed(Runnable {
            if(Pref.FirstLogiForTheDayTag){
                Pref.FirstLogiForTheDayTag = false

                if(!Pref.LastVideoPlay_TopicName.equals("")){
                    VideoPlayLMS.loadedFrom = "LMSDASHBOARD"
                    CustomStatic.VideoPosition = Pref.LastVideoPlay_VidPosition.toInt()
                    Pref.videoCompleteCount = "0"
                    Handler().postDelayed(Runnable {
                        setHomeClickFalse()
                        (mContext as DashboardActivity).loadFragment(FragType.VideoPlayLMS, true, Pref.LastVideoPlay_TopicID +"~"+ Pref.LastVideoPlay_TopicName/*+"~"+position*/)
                    }, 1000)
                }else{
                    Handler().postDelayed(Runnable {
                        VideoPlayLMS.loadedFrom = "LMSDASHBOARD"
                        gotoVideoPage()
                    }, 1000)

                }
            }
        }, 600)

        if (Pref.like_count!=0 || Pref.comment_count!=0 || Pref.share_count!=0 || Pref.correct_answer_count!=0 || Pref.wrong_answer_count!=0){
                contentCountSaveAPICalling()
        }



    }

    override fun onResume() {
        super.onResume()
        try {
            var votVIwedL = AppDatabase.getDBInstance()!!.lmsNotiDao().getNotViwed(false) as ArrayList<LMSNotiEntity>
            if(votVIwedL.size !=0){
                (mContext as DashboardActivity).tv_noti_count.visibility = View.VISIBLE
                (mContext as DashboardActivity).tv_noti_count.text = votVIwedL.size.toString()
            }else{
                (mContext as DashboardActivity).tv_noti_count.visibility = View.GONE
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun contentCountSaveAPICalling() {

        try {
            Timber.d("contentCountSaveAPICalling call" + AppUtils.getCurrentDateTime())

            var obj = ContentCountSave_Data()
            obj.user_id = Pref.user_id.toString()
            obj.save_date = AppUtils.getCurrentDateyymmdd()
            obj.like_count = Pref.like_count
            obj.comment_count = Pref.comment_count
            obj.share_count = Pref.share_count
            obj.correct_answer_count = Pref.correct_answer_count
            obj.wrong_answer_count = Pref.wrong_answer_count
            obj.content_watch_count = Pref.content_watch_count

            val repository = LMSRepoProvider.getTopicList()
            BaseActivity.compositeDisposable.add(
                repository.saveContentCount(obj)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({ result ->
                        val response = result as BaseResponse
                        if (response.status == NetworkConstant.SUCCESS) {
                            try {
                                Pref.like_count = 0
                                Pref.comment_count = 0
                                Pref.share_count = 0
                                Pref.correct_answer_count = 0
                                Pref.wrong_answer_count = 0
                                Pref.content_watch_count = 0
                            }catch (ex:Exception){
                                ex.printStackTrace()
                            }
                        }else{
                            (mContext as DashboardActivity).showSnackMessage(getString(R.string.no_data_found))
                        }
                    }, { error ->
                        (mContext as DashboardActivity).showSnackMessage(getString(R.string.something_went_wrong))
                    })
            )
        }
        catch (ex: Exception) {
            ex.printStackTrace()
            (mContext as DashboardActivity).showSnackMessage(getString(R.string.something_went_wrong))
        }

    }

    fun getTopicLAssigened() {
        try {
            Timber.d("deleteImei call" + AppUtils.getCurrentDateTime())
            val repository = LMSRepoProvider.getTopicList()
            BaseActivity.compositeDisposable.add(
                repository.getTopics(Pref.user_id!!)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({ result ->
                        val response = result as TopicListResponse
                        //var responseF = response.topic_list.filter { it.topic_parcentage !=0 }
                        if (response.status == NetworkConstant.SUCCESS) {
                            courseList = ArrayList<LmsSearchData>()
                            courseListLearning = ArrayList<LmsSearchData>()
                            for (i in 0..response.topic_list.size - 1) {
                                if (response.topic_list.get(i).video_count!= 0) {
                                   // if (response.topic_list.get(i).topic_parcentage!= 0) {
                                        courseList = courseList + LmsSearchData(
                                            response.topic_list.get(i).topic_id.toString(),
                                            response.topic_list.get(i).topic_name
                                        )
                                   // }
                                }
                            }
                            tv_content.setText(courseList.size.toString()+" Topics")
                            //tv_content_learning.setText(courseListLearning.size.toString()+" Topics")

                           /* val fullText = tv_content.text.toString()
                            val parts = fullText.split("\n")

                            val largeText = parts[0]
                            val smallText = parts[1]
                            val spannableString = SpannableString(fullText)
                            // Set the size of "1000"
                            spannableString.setSpan(
                                RelativeSizeSpan(1.2f), // 4 times the default size
                                0,
                                largeText.length,
                                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                            )
                            // Make "1000" bold
                            spannableString.setSpan(
                                StyleSpan(Typeface.BOLD),
                                0,
                                largeText.length,
                                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                            )
                            // Set the size of "Contents"
                            spannableString.setSpan(
                                RelativeSizeSpan(0.98f), // default size
                                largeText.length + 1, // +1 to account for the newline character
                                smallText.length,
                                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                            )
                            tv_content.text = spannableString*/

                        }else{
                            //(mContext as DashboardActivity).showSnackMessage(getString(R.string.no_data_found))

                        }
                    }, { error ->
                        (mContext as DashboardActivity).showSnackMessage(getString(R.string.something_went_wrong))
                    })
            )
        } catch (ex: Exception) {
            ex.printStackTrace()
            (mContext as DashboardActivity).showSnackMessage(getString(R.string.something_went_wrong))
        }
    }

    fun getTopicLearningAssigened() {
        try {
            Timber.d("deleteImei call" + AppUtils.getCurrentDateTime())
            val repository = LMSRepoProvider.getTopicList()
            BaseActivity.compositeDisposable.add(
                repository.getTopics(Pref.user_id!!)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({ result ->
                        val response = result as TopicListResponse
                        //var responseF = response.topic_list.filter { it.topic_parcentage !=0 }
                        if (response.status == NetworkConstant.SUCCESS) {
                            courseList = ArrayList<LmsSearchData>()
                            courseListLearning = ArrayList<LmsSearchData>()
                            for (i in 0..response.topic_list.size - 1) {
                                if (response.topic_list.get(i).video_count!= 0) {
                                    if (response.topic_list.get(i).topic_parcentage!= 0) {
                                        courseListLearning = courseListLearning + LmsSearchData(
                                            response.topic_list.get(i).topic_id.toString(),
                                            response.topic_list.get(i).topic_name
                                        )
                                    }

                                }
                            }
                            //tv_content.setText(courseList.size.toString()+" Topics")
                            tv_content_learning.setText(courseListLearning.size.toString()+" Topics")

                            /* val fullText = tv_content.text.toString()
                             val parts = fullText.split("\n")

                             val largeText = parts[0]
                             val smallText = parts[1]
                             val spannableString = SpannableString(fullText)
                             // Set the size of "1000"
                             spannableString.setSpan(
                                 RelativeSizeSpan(1.2f), // 4 times the default size
                                 0,
                                 largeText.length,
                                 Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                             )
                             // Make "1000" bold
                             spannableString.setSpan(
                                 StyleSpan(Typeface.BOLD),
                                 0,
                                 largeText.length,
                                 Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                             )
                             // Set the size of "Contents"
                             spannableString.setSpan(
                                 RelativeSizeSpan(0.98f), // default size
                                 largeText.length + 1, // +1 to account for the newline character
                                 smallText.length,
                                 Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                             )
                             tv_content.text = spannableString*/

                        }else{
                            //(mContext as DashboardActivity).showSnackMessage(getString(R.string.no_data_found))

                        }
                    }, { error ->
                        (mContext as DashboardActivity).showSnackMessage(getString(R.string.something_went_wrong))
                    })
            )
        } catch (ex: Exception) {
            ex.printStackTrace()
            (mContext as DashboardActivity).showSnackMessage(getString(R.string.something_went_wrong))
        }
    }


    private fun getMyLarningTopicListInfoAPI() {
        try {
            Timber.d("deleteImei call" + AppUtils.getCurrentDateTime())
            val repository = LMSRepoProvider.getTopicList()
            BaseActivity.compositeDisposable.add(
                repository.getTopics("0")
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({ result ->
                        val response = result as TopicListResponse
                        if (response.status == NetworkConstant.SUCCESS) {
                            courseList = ArrayList<LmsSearchData>()
                            for (i in 0..response.topic_list.size - 1) {
                                if (response.topic_list.get(i).video_count!= 0 && response.topic_list.get(i).topic_parcentage!=0) {
                                    courseList = (courseList + LmsSearchData(
                                        response.topic_list.get(i).topic_id.toString(),
                                        response.topic_list.get(i).topic_name,
                                        response.topic_list.get(i).video_count,
                                        response.topic_list.get(i).topic_parcentage
                                    )) as ArrayList<LmsSearchData>
                                }
                            }
                            tv_content_learning.setText(courseList.size.toString())

                        }else{
                          //  (mContext as DashboardActivity).showSnackMessage(getString(R.string.no_data_found))

                        }
                    }, { error ->
                        (mContext as DashboardActivity).showSnackMessage(getString(R.string.something_went_wrong))
                    })
            )
        } catch (ex: Exception) {
            ex.printStackTrace()
            (mContext as DashboardActivity).showSnackMessage(getString(R.string.something_went_wrong))
        }
    }

    fun getTopicL() {
        try {
            Timber.d("deleteImei call" + AppUtils.getCurrentDateTime())
            val repository = LMSRepoProvider.getTopicList()
            BaseActivity.compositeDisposable.add(
                repository.getTopics("0")
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({ result ->
                        val response = result as TopicListResponse
                        if (response.status == NetworkConstant.SUCCESS) {
                            courseList = ArrayList<LmsSearchData>()
                            for (i in 0..response.topic_list.size - 1) {
                                if (response.topic_list.get(i).video_count!= 0) {
                                    courseList = courseList + LmsSearchData(
                                        response.topic_list.get(i).topic_id.toString(),
                                        response.topic_list.get(i).topic_name
                                    )
                                }
                            }
                           tv_content_knowledge.setText(courseList.size.toString()+" Topics"/*+"\nContents"*/)
                            /*val fullText2 = tv_content_knowledge.text.toString()
                           val parts2 = fullText2.split("\n")

                           val largeText2 = parts2[0]
                           val smallText2 = parts2[1]
                           val spannableString2 = SpannableString(fullText2)
                           spannableString2.setSpan(
                               RelativeSizeSpan(1.2f), // 4 times the default size
                               0,
                               largeText2.length,
                               Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                           )
                           spannableString2.setSpan(
                               StyleSpan(Typeface.BOLD),
                               0,
                               largeText2.length,
                               Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                           )
                           spannableString2.setSpan(
                               RelativeSizeSpan(0.98f), // default size
                               largeText2.length + 1, // +1 to account for the newline character
                               smallText2.length,
                               Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                           )
                           tv_content_knowledge.text = spannableString2
*/
                        }else{
                            (mContext as DashboardActivity).showSnackMessage(getString(R.string.no_data_found))
                        }
                    }, { error ->
                        (mContext as DashboardActivity).showSnackMessage(getString(R.string.something_went_wrong))
                    })
            )
        } catch (ex: Exception) {
            ex.printStackTrace()
            (mContext as DashboardActivity).showSnackMessage(getString(R.string.something_went_wrong))
        }
    }
    /*companion object {
        fun getInstance(objects: Any): MyLearningFragment {
            val fragment = MyLearningFragment()
            return fragment
        }
    }*/
    override fun onClick(v: View?) {
        when(v?.id){
            ll_knowledgeHub.id -> {
                setHomeClickFalse()
                (mContext as DashboardActivity).loadFragment(FragType.SearchLmsKnowledgeFrag, true, "")
            }

            ll_myLearning.id -> {
                setHomeClickFalse()
                (mContext as DashboardActivity).loadFragment(FragType.MyLearningTopicList, true, "")
            }

            cv_lms_leaderboard.id -> {
                setHomeClickFalse()
                (mContext as DashboardActivity).loadFragment(FragType.LeaderboardLmsFrag, true, "")
            }

            ll_lms_mylearning.id -> {
                setHomeClickFalse()
                (mContext as DashboardActivity).loadFragment(FragType.MyLearningTopicList, true, "")
            }

            ll_lms_leaderboard.id -> {
                setHomeClickFalse()
                (mContext as DashboardActivity).loadFragment(FragType.MyLearningFragment, true, "")
            }

            ll_lms_knowledgehub.id -> {
                setHomeClickFalse()
                (mContext as DashboardActivity).loadFragment(FragType.SearchLmsKnowledgeFrag, true, "")
            }
            ll_lms_performance.id -> {
                setHomeClickFalse()
                (mContext as DashboardActivity).loadFragment(FragType.MyPerformanceFrag, true, "")

            }
            cv_frag_search_mylearning_root.id -> {
                setHomeClickFalse()
                (mContext as DashboardActivity).loadFragment(FragType.MyPerformanceFrag, true, "")
            }
        }
    }

    fun gotoVideoPage() {
        try {
            println("tag_call_api gotoVideoPage")
            val repository = LMSRepoProvider.getTopicList()
            BaseActivity.compositeDisposable.add(
                repository.getTopics(Pref.user_id!!)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({ result ->
                        val response = result as TopicListResponse
                        println("tag_call_api response ${response.status}")
                        if (response.status == NetworkConstant.SUCCESS) {
                            if(response.topic_list != null){

                                try {
                                    var filterL =response.topic_list.filter { it.topic_parcentage != 100 }
                                    getVideoTopicWise(filterL.get(0))
                                    /*var firstObj = response.topic_list.get(0)
                                    VideoPlayLMS.previousFrag = FragType.SearchLmsFrag.toString()
                                    VideoPlayLMS.loadedFrom = "LMSDASHBOARD"
                                    Pref.videoCompleteCount = "0"
                                    Handler().postDelayed(Runnable {
                                        (mContext as DashboardActivity).loadFragment(FragType.VideoPlayLMS, true, firstObj.topic_id.toString()+"~"+firstObj.topic_name)
                                    }, 500)*/
                                }catch (e:Exception){
                                    e.printStackTrace()
                                }
                                 }
                        }else{

                        }
                    }, { error ->
                        (mContext as DashboardActivity).showSnackMessage(getString(R.string.something_went_wrong))
                    })
            )
        } catch (ex: Exception) {
            ex.printStackTrace()
            (mContext as DashboardActivity).showSnackMessage(getString(R.string.something_went_wrong))
        }
    }

    private fun getVideoTopicWise(topicList: TopicList) {

        try {
            Timber.d("deleteImei call" + AppUtils.getCurrentDateTime())
            val repository = LMSRepoProvider.getTopicList()
            BaseActivity.compositeDisposable.add(
                repository.getTopicsWiseVideo(Pref.user_id!!, topicList.topic_id.toString())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({ result ->
                        val response = result as VideoTopicWiseResponse
                        if (response.status == NetworkConstant.SUCCESS) {
                            try {
                                if (response.content_list != null && response.content_list.size > 0) {

                                    var incompltContentL = response.content_list.filter { it.Watch_Percentage != "100" }
                                    for (i in 0 .. response.content_list.size-1){
                                        CustomStatic.VideoPosition =  CustomStatic.VideoPosition+1
                                        if (response.content_list.get(i).Watch_Percentage != "100"){
                                            break
                                        }
                                    }

                                    VideoPlayLMS.previousFrag = FragType.SearchLmsFrag.toString()
                                    VideoPlayLMS.loadedFrom = "LMSDASHBOARD"
                                    Pref.videoCompleteCount = "0"
                                    Handler().postDelayed(Runnable {
                                        setHomeClickFalse()
                                        (mContext as DashboardActivity).loadFragment(FragType.VideoPlayLMS, true, topicList.topic_id.toString()+"~"+topicList.topic_name)
                                    }, 500)
                                } else {
                                    Toast.makeText(mContext, "No video found", Toast.LENGTH_SHORT)
                                        .show()

                                }
                            } catch (ex: Exception) {
                                ex.printStackTrace()
                            }
                        } else {

                            (mContext as DashboardActivity).showSnackMessage(getString(R.string.no_data_found))

                        }
                    }, { error ->

                        (mContext as DashboardActivity).showSnackMessage(getString(R.string.something_went_wrong))
                    })
            )
        } catch (ex: Exception) {
            ex.printStackTrace()
            (mContext as DashboardActivity).showSnackMessage(getString(R.string.something_went_wrong))
        }
    }

    fun setHomeClickFalse(){
        CustomStatic.IsHomeClick = false
    }

}