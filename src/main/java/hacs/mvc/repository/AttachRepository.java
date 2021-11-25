package hacs.mvc.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import hacs.mvc.domain.Attach;

@Repository
public interface AttachRepository {
	
	public int insertAttach(List<Attach> attachList);	

	public Attach selectAttachDetail(Long idx);

	public int deleteAttach(Long boardIdx);

	public List<Attach> selectAttachList(Long boardIdx);

	public int selectAttachTotalCount(Long boardIdx);
}
