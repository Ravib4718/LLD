class File{
    String name;
    int type;
    int size;
    boolean isDirectory;
    File childrens[];
}

abstract class Filter{
    abstract boolean apply(File file);
}

class MinSizeFilter extends Filter{
    int minSize;
    public MinSizeFilter(int minSize){
        this.minSize=minSize;
    }

    @Override
    boolean apply(File file){
        return file.size>minSize;
    }
}

class TypeFilter extends Filter {

    int type;

    public TypeFilter(int type) {
        this.type = type;
    }

    @Override
    boolean apply(File file) {
        return file.type == type;
    }
}


class FindCommand {

    public List<File> findWithFilters(File directory, List<Filter> filters) {
        if (!directory.isDirectory) {
            return new NotADirectoryException();
        }
        List<File> output = new ArrayList<>();
        findWithFilters(directory, filters, output);
        return output;
    }

    private void findWithFilters(File directory, List<Filter> filters, List<File> output) {
        if(directory.childrens==null){
            return;
        }
        for(File file : directory.childrens){
            if(file.isDirectory){
                findWithFilters(directory,filters,output);
            }
            else{
                boolean selectFile;
                for(Filter filter :filters){
                    if(filter.apply(file)){
                        continue;
                    }
                    else{
                        selectFile=false;
                    }
                }
                if(selectFile){
                    output.add(file);
                }
            }
        }
    }
}