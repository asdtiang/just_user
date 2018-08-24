import java.io.File;

import javax.swing.filechooser.FileFilter;

public class DocumentsFilter extends FileFilter {

	@Override
	public boolean accept(File f) {
	    if (f.isDirectory()) {
	        return true;
	    }

	    String extension = Utils.getExtension(f);
	    if (extension != null) {
	        if (extension.equals(Utils.doc) ||
	            extension.equals(Utils.docx) ||
	            extension.equals(Utils.pdf) ||
	            extension.equals(Utils.txt)) {
	                return true;
	        } else {
	            return false;
	        }
	    }

	    return false;
	}

	@Override
	public String getDescription() {
		
		return "Printable documents (.doc, .docx, .pdf, .txt)";
	}

}
