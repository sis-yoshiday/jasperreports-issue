/*
 * Copyright (c) 2017. Sports IT Solution, Inc.
 */

import java.io.InputStream;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OriginalModel {

  private List<ListItem> mediaTypeMovies;
  private List<ListItem> mediaTypePictures;
  private String otherDescription;
  private String note;
  private String classificationName;
  private String applicantUserName;
  private String managementId;
  private String submissionName;
  private String organizationName;
  private String submittedAt;
  private String planOverview;
  private List<ListItem> emblems;
  private List<ListItem> emblemFormats;
  private String jointUseMark;
  private String useStartedAt;
  private String useEndedAt;
  private InputStream stamp;
  private String approvedAt;
  private String referencesUpdatedAt;
  private String references;
}
