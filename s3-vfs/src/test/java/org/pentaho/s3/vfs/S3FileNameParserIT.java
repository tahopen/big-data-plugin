/*!
 * Copyright 2010 - 2022 Hitachi Vantara.  All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.pentaho.s3.vfs;

import org.apache.commons.vfs2.provider.FileNameParser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * created by: rfellows date:       5/25/12
 */
public class S3FileNameParserIT {

  @Test
  public void testParseUri() throws Exception {
    FileNameParser parser = S3FileNameParser.getInstance();
    String origUri = "s3://fooBucket/rcf-emr-staging";
    S3FileName filename =
      (S3FileName) parser.parseUri( null, null, origUri );

    assertEquals( "fooBucket", filename.getBucketId() );

    assertEquals( origUri, filename.getURI() );
  }
}
