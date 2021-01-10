package nl.victorfdt.projectmanagerbackend.converter;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

import java.util.ArrayList;
import java.util.List;

public class DozerConverter {

    public static Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    public static <S, D> D parse(S source, Class<D> destination) {
        return mapper.map(source, destination);
    }

    public static <S, D> List<D> parse(List<S> source, Class<D> destination) {
        List<D> destinationList = new ArrayList<D>();
        for (Object o : source) {
            destinationList.add(mapper.map(o, destination));
        }
        return destinationList;
    }
}
