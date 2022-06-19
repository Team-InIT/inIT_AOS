package com.init_android.app.data

import com.init_android.app.data.request.*
import com.init_android.app.data.request.mypage.*
import com.init_android.app.data.request.project.RequestApplyProject
import com.init_android.app.data.request.project.RequestApproveProject
import com.init_android.app.data.request.project.RequestProjectDetail
import com.init_android.app.data.request.project.RequestProjectMember
import com.init_android.app.data.request.todo.RequestToDoMember
import com.init_android.app.data.request.todo.RequestWriteToDo
import com.init_android.app.data.response.*
import com.init_android.app.data.response.mypage.*
import com.init_android.app.data.response.project.ResponseApplyProject
import com.init_android.app.data.response.project.ResponseProjectDetail
import com.init_android.app.data.response.project.approve.*
import com.init_android.app.data.response.project.ready.*
import com.init_android.app.data.response.todo.ResponseAllToDo
import com.init_android.app.data.response.todo.ResponseToDoMember
import com.init_android.app.data.response.todo.ResponseWriteToDo
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface InitService {
    @POST("/login")
    suspend fun postLogin(
        @Body body: RequestSignIn
    ): ResponseSignIn

    @POST("/idCheck")
    fun postIdCheck(
        @Body body: RequestIdCheck
    ): Call<ResponseIdCheck>

    @POST("/signUp")
    fun postRegister(
        @Body body: RequestSignUp
    ): Call<ResponseSignUp>

    @POST("/addProject")
    suspend fun postAddProject(
        @Body body: RequestAddProject
    ): ResponseAddProject

    @POST("/home")
    suspend fun postHome(@Body body: RequestHome): ResponseNewHome


    @POST("/myPage")
    suspend fun postMyPageInfo(@Body body: RequestMyInfo): ResponseMyInfo

    @POST("/getRecommenedProject")
    suspend fun postRecoProject(@Body body: RequestRecoProject): ResponseRecoProject

    //링크 수정 서버통신
    @POST("/updateLink")
    suspend fun postModifyLink(@Body body: RequestModifyLink): ResponseModifyLink

    //기본 정보 수정 서버 통신
    @POST("/editBasicInfo")
    suspend fun postModifyBasicInfo(@Body body: RequestModifyBasicInfo): ResponseModifyBasicInfo

    //회원 탈퇴 서버통신
    @POST("/withdraw")
    suspend fun postQuit(@Body body: RequestQuit): ResponseQuit


    //내 정보수정 서버통신
    @Multipart
    @POST("/updateProfile")
    suspend fun postUpdateProfile(
        @Part file: MultipartBody.Part,
        @Part("mNum") mNum: RequestBody,
        @Part("mName") mName: RequestBody,
        @Part("mPosition") mPosition: RequestBody,
        @Part("mLevel") mLevel: RequestBody,
        @Part("mIntroduction") mIntroduction: RequestBody
    ): ResponseUpdateProfile


    //스택 수정 서버통신
    @POST("/updateStack")
    suspend fun postModifyStack(@Body body: RequestModifyStack) : ResponseModifyStack


    //마이페이지 프로젝트 갯수 서버통신
    @POST("/countProject")
    suspend fun postCountProject(@Body body: RequestCountProject): ResponseCountProject

    //피드 참여한 프로젝트 바텀시트
    @POST("/finishedProject")
    suspend fun postFinishProject(@Body body: RequestFinishProject): ResponseFinishProject

    //프로젝트 상세보기
    @POST("/detailProject")
    suspend fun postProjectDetail(@Body body: RequestProjectDetail): ResponseProjectDetail

    //프로젝트 지원하기
    @POST("/apply")
    suspend fun postApplyProject(@Body body: RequestApplyProject): ResponseApplyProject

    // 피드 리스트 보기
    @GET("/getAllFeed")
    suspend fun getAllFeed(): ResponseFeed

    // 피드 등록
    @Multipart
    @POST("/addFeed")
    suspend fun postAddFeed(
        @Part file: MultipartBody.Part,
        @Part("fTitle") fTitle: RequestBody,
        @Part("fDescription") fDescription: RequestBody,
        @Part("fLink") fLink: RequestBody,
        @Part("mNum") mNum: RequestBody,
        @Part("pNum") pNum: RequestBody,
        @Part("fType") fType: RequestBody
    ): ResponseBase

    // 피드 삭제
    @POST("/deleteFeed")
    suspend fun postDeleteFeed(@Body body: RequestDeleteFeed): ResponseBase


    //팀원 조회
    @POST("/teamMember")
    suspend fun postProjectMember(@Body body: RequestProjectMember): ResponseProjectMember


    // 피드 상세보기
    @POST("/detailFeed")
    suspend fun postDetailFeed(@Body requestFeedDetail: RequestFeedDetail):ResponseFeedDetail


    //승인대기
    @POST("/myWaitingApproval")
    suspend fun postWaitingApproval(@Body requestWaitingApproval: RequestWaitingApproval) : ResponsemyWaitingApproval

    // 내 피드 보기
    @POST("/myFeeds")
    suspend fun postMyFeeds(@Body requestMyFeed: RequestMyFeed) : ResponseMyFeed

    //승인여부 조회
    @POST("/myIngProject")
    suspend fun postIngProject(@Body requestWaitingApproval: RequestWaitingApproval) : ResponsemyWaitingApproval

    //끝난 프로젝트
    @POST("/myEndProject")
    suspend fun postEndProject(@Body requestWaitingApproval: RequestWaitingApproval) : ResponsemyWaitingApproval


    //찜 리스트
    @POST("/myZzimList")
    suspend fun postZzimProject(@Body requestWaitingApproval: RequestWaitingApproval) : ResponsemyWaitingApproval

    //업로드 리스트
    @POST("/myUploadProject")
    suspend fun postUploadProject(@Body requestWaitingApproval: RequestWaitingApproval) : ResponseUpload


    //기획파트 TODO 조회
    @POST("/allTodoPlan")
    suspend fun postLookUpPlanTodo(@Body requestProjectMember : RequestProjectMember) : ResponseAllToDo

    //디자인파트 TODO 조회
    @POST("/allTodoDesign")
    suspend fun postLookUpDesignTodo(@Body requestProjectMember : RequestProjectMember) : ResponseAllToDo

    //Ios파트 TODO 조회
    @POST("/allTodoIos")
    suspend fun postLookUpIosTodo(@Body requestProjectMember : RequestProjectMember) : ResponseAllToDo

    //Aos파트 TODO 조회
    @POST("/allTodoAos")
    suspend fun postLookUpAosTodo(@Body requestProjectMember : RequestProjectMember) : ResponseAllToDo

    //웹파트 TODO 조회
    @POST("/allTodoWeb")
    suspend fun postLookUpWebTodo(@Body requestProjectMember : RequestProjectMember) : ResponseAllToDo

    //게임파트 TODO 조회
    @POST("/allTodoGame")
    suspend fun postLookUpGameTodo(@Body requestProjectMember : RequestProjectMember) : ResponseAllToDo

    //서버 파트 TODO 조회
    @POST("/allTodoServer")
    suspend fun postLookUpServerTodo(@Body requestProjectMember : RequestProjectMember) : ResponseAllToDo


    // 검색 모집중 프로젝트 리스트
    @GET("/recrutingProject")
    suspend fun getRecrutingProject(): ResponseGetRecruitingProject

    // 검색 모집완료 프로젝트 리스트
    @GET("/notRecruitingProject")
    suspend fun getNotRecruitingProject(): ResponseGetNotRecruitingProject

    // 검색 파트원 리스트
    @GET("/userAll")
    suspend fun getUserAll(): ResponseGetUserAll

    // 모집중 프로젝트 리스트 검색
    @POST("/searchIng")
    suspend fun postSearchIng(@Body requestSearchIng: RequestSearchIng) : ResponseSearchResult

    // 모집완료 프로젝트 리스트 검색
    @POST("/searchEd")
    suspend fun postSearchEd(@Body requestSearchEd: RequestSearchEd) : ResponseSearchResult

    // 팀평가 미평가 팀원 리스트 조회
    @POST("/notEveluate")
    suspend fun postNotEveluate(@Body requestNotEveluate: RequestNotEveluate):ResponseNotEveluate

    // 평가 입력
    @POST("/addEvaluate")
    suspend fun postAddEveluate(@Body requestAddEvaluate: RequestAddEvaluate):ResponseBase

    // 팀평가 평가 팀원 리스트 조회
    @POST("/alreadyEvaluate")
    suspend fun postAlreadyEvaluate(@Body requestAlreadyEvaluate: RequestAlreadyEvaluate):ResponseAlreadyEvaluate

    // 평가된 팀원 개별요소 조회
    @POST("/checkEvaluation")
    suspend fun postCheckEvaluation(@Body requestCheckEvaluation: RequestCheckEvaluation):ResponseCheckEvaluation

    // 팀원 평가 삭제
    @POST("/deleteEvaluation")
    suspend fun postDeleteEvaluation(@Body requestDeleteEvaluation: RequestDeleteEvaluation):ResponseBase

    // 투두 태그할 멤버 보기
    @POST("/todoMember")
    suspend fun postToDoMember(@Body requestToDoMember: RequestToDoMember) : ResponseToDoMember

    //투두 추가
    @POST("/addTodo")
    suspend fun postWriteTodo(@Body requestWriteToDo: RequestWriteToDo) : ResponseWriteToDo

    //마이페이지 팀원 평가 확인
    @POST("/myEvaluation")
    suspend fun postMyEvaluation(@Body requestWaitingApproval: RequestWaitingApproval) : ResponseEvaluation

    //내 프로젝트 팀원 승인/승인 전 - 기획
    @POST("/myCrewPlan")
    suspend fun postCrewPlan(@Body requestProjectMember: RequestProjectMember) : ResponseReadyPlan

    //디자인
    @POST("/myCrewDesign")
    suspend fun postCrewDesign(@Body requestProjectMember: RequestProjectMember) : ResponseReadyDesign

    //IOS
    @POST("/myCrewIos")
    suspend fun postCrewIos(@Body requestProjectMember: RequestProjectMember) : ResponseReadyIos

    //AOS
    @POST("/myCrewAos")
    suspend fun postCrewAos(@Body requestProjectMember: RequestProjectMember) : ResponseReadyAos

    //Web
    @POST("/myCrewWeb")
    suspend fun postCrewWeb(@Body requestProjectMember: RequestProjectMember) : ResponseReadyWeb

    //Game
    @POST("/myCrewGame")
    suspend fun postCrewGame(@Body requestProjectMember: RequestProjectMember) : ResponseReadyGame

    //Server
    @POST("/myCrewServer")
    suspend fun postCrewServer(@Body requestProjectMember: RequestProjectMember) : ResponseReadyServer

    //프로젝트 승인
    @POST("/approve")
    suspend fun postApprove(@Body requestApproveProject: RequestApproveProject) : ResponseApproveProject

    //프로젝트 거절
    @POST("/reject")
    suspend fun postReject(@Body requestApproveProject: RequestApproveProject) : ResponseApproveProject

}