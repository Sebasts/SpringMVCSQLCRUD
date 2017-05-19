package io.hellsing.data;

public interface MemoDAO {
	public String editMemoBody(Memo memo);
	public String createMemo();
	public void deleteMemo(Memo memo);
	public Memo editMemoTitle(Memo memo);
	public void loadMemos();
	public void saveMemos();
}
