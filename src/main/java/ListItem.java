/*
 * Copyright (c) 2017. Sports IT Solution, Inc.
 */

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ListItem {

  private final String checked;

  private final String name;
}
