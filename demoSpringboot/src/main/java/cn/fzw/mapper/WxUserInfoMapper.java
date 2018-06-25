package cn.fzw.mapper;

import org.apache.ibatis.annotations.Param;

import cn.fzw.weixin.vo.WxUserInfo;

public interface WxUserInfoMapper {

	 WxUserInfo selectByOpenid(@Param("openid")String openid);

	 void insertInto(@Param("subscribe")String subscribe,@Param("openid") String openid, @Param("nickname")String nickname, 
			 @Param("sex")String sex,@Param("city") String city, @Param("country")String country,
			 @Param("province")String province,@Param("language")String language, @Param("headimgurl")String headimgurl,
			 @Param("subscribe_time")String subscribe_time, @Param("unionid")String unionid,@Param("remark")String remark, 
			 @Param("groupid")String groupid,@Param("tagid_list")String tagid_list,@Param("subscribe_scene")String subscribe_scene,
			 @Param("qr_scene")String qr_scene, @Param("qr_scene_str")String qr_scene_str);

	 void updateById(@Param("id")int id,@Param("subscribe")String subscribe,@Param("openid") String openid, @Param("nickname")String nickname, 
			 @Param("sex")String sex,@Param("city") String city, @Param("country")String country,
			 @Param("province")String province,@Param("language")String language, @Param("headimgurl")String headimgurl,
			 @Param("subscribe_time")String subscribe_time, @Param("unionid")String unionid,@Param("remark")String remark, 
			 @Param("groupid")String groupid,@Param("tagid_list")String tagid_list,@Param("subscribe_scene")String subscribe_scene,
			 @Param("qr_scene")String qr_scene, @Param("qr_scene_str")String qr_scene_str);

}
