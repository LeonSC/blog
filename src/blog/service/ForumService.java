package blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blog.dao.BlockDao;
import blog.model.Auth;
import blog.model.Block;
import blog.startup.FCache;

@Service
public class ForumService {

	@Autowired
	private BlockDao blockDao;

	/**
	 * 保存一个节点
	 * 
	 * @param okey
	 * @param name 不能为空
	 * @param intro
	 * @param icon
	 * @param order
	 * @param loginVisible
	 * @return
	 */
	public int saveOrUpdateBlock(String bmid, String okey, String name, String intro, String icon, Integer order, Integer loginVisible) {
		if (name == null) {
			return -1;
		}
		Block b = new Block();
		b.setBM_ID(bmid);
		Auth need = new Auth();
		need.setLoginVisible(loginVisible == null ? 0 : loginVisible);
		b.setAuth(need);
		b.setOkey(okey == null || okey.equals("") ? "0" : okey);
		b.setName(name);
		b.setIntro(intro == null ? "" : intro);
		b.setIcon(icon == null ? "" : icon);
		b.setOrder(order == null ? 0 : order);
		if (b.getBM_ID() == null) {
			this.blockDao.save(b);// 新增
		} else {
			this.blockDao.update(b);
		}
		// 刷新
		FCache.getCache().initBlockCache();
		return 0;
	}
}
