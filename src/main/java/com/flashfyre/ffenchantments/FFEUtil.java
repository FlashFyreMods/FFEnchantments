package com.flashfyre.ffenchantments;

import java.util.List;
import java.util.function.Function;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;

import net.minecraft.util.ExtraCodecs;

public class FFEUtil {
	
	public static <T> Codec<List<T>> listOrElementCodec(final Codec<T> codec) {
	       return Codec.either(codec, ExtraCodecs.nonEmptyList(codec.listOf()))
	                .xmap(either -> either.map(ImmutableList::of, Function.identity()),
	                        list -> list.size() == 1 ? Either.left(list.get(0)) : Either.right(list));
	}

}
